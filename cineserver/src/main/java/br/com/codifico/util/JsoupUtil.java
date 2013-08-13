package br.com.codifico.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import br.com.codifico.entidades.Cinema;
import br.com.codifico.entidades.Endereco;
import br.com.codifico.entidades.FilmeCartaz;
import br.com.codifico.entidades.Horario;

public class JsoupUtil {
	private static int alternaHeader;
	private static Calendar gc = new GregorianCalendar();

	public static void fazRequestPorCinema(String url) throws IOException {
		List<Cinema> listaDeCinemas = new ArrayList<Cinema>();

		Cinema cinema = null;
		Endereco endereco = null;
		FilmeCartaz filme = null;
		Horario horario = null;

		PropertyConfigurator.configure(log4jConfigFile);

		Elements elemsBlocoCinema = null;
		Elements elemsBlocoFilmes = null;
		Document doc = null;

		int qtde = 0;
		int pagina = 1;
		boolean ultimaPagina = false;

		while (!ultimaPagina) {

			doc = configuraEFazRequest(url, pagina);

			// bloco com nome dos cinemas
			elemsBlocoCinema = doc.getElementsByClass(("j_entity_container"));
			// bloco com nome dos filmes
			elemsBlocoFilmes = doc.getElementsByClass("w-showtimetabs");
			// é obrigatorio ser a mesma quantidade de bloco de filmes e cinemas
			if (elemsBlocoCinema.size() != elemsBlocoFilmes.size())
				throw new RuntimeException("Não bate a quantidade");

			// Recupera cinema
			int contFilme = 0;
			for (Element elemCine : elemsBlocoCinema) {
				cinema = new Cinema();
				endereco = new Endereco();

				Element cine = elemCine.getElementsByAttribute("data-entities")
						.first();
				Element enderecoCinema = elemCine.getElementsByClass("lighten")
						.get(1);
				imprime(true, cine, enderecoCinema);

				cinema.setNome(formataEArmazena(cine));
				endereco.setDados(formataEArmazena(enderecoCinema));
				cinema.setEndereco(endereco);

				// Recupera todos filmes de cada cinema iterado
				Element elementoComFilmes = elemsBlocoFilmes.get(contFilme);

				// Recupera cada filme (de um cinema), para 3 dias
				for (int i = 0; i <= 2; i++) {
					String diaDaSemana = diaDaSemana(i);

					Element elemFilmesDia = elementoComFilmes.select(
							"div.tabs_box_pan.item-" + i).first();
					if (elemFilmesDia == null) {
						log.info("Parece que não há sessão na " + diaDaSemana
								+ url + "?pagina=" + pagina, new Exception());
						log.info("Passando para próximo dia...");
						continue;

					}

					Elements elemsFilmes = elemFilmesDia
							.getElementsByClass("w-shareyourshowtime");

					// Totos os filmes (do cinema) do dia que está sendo iterado
					for (Element elemFilme : elemsFilmes) {
						horario = new Horario();
						filme = new FilmeCartaz(i);

						String attrFilme = elemFilme.attr("data-movie");
						Element elemHora = elemFilme.nextElementSibling();
						Elements elemsHora = elemHora
								.getElementsByAttribute("data-times");

						for (Element hora : elemsHora) {
							horario.addHorario(formataEArmazena(hora));
						}
						// JSON - Não utilizado ainda
						imprime(attrFilme);

						filme.setNome(formataEArmazena(elemFilme));
						filme.setHorario(horario);

						cinema.addFilme(filme);
					}
				}
				listaDeCinemas.add(cinema);

				contFilme++;
			}
			++qtde;

			// último request é feito apos a última página válida
			if (elemsBlocoCinema.size() == 0) {
				elemsBlocoCinema = doc
						.getElementsMatchingOwnText("Nenhum cinema tem hor.rios que atendam seus crit.rios");
				if (elemsBlocoCinema.size() > 0)
					ultimaPagina = true;
				else
					throw new RuntimeException("Término Inesperado");
			}
			System.out.println("Fim da Página " + pagina++ + "\n");
		}
		System.out.println(qtde + " Cinemas");

	}

	public static String diaDaSemana(int qtdeAcrescimo) {
		String labelDiaDaSemana = null;
		gc.setTime(new Date());
		gc.add(Calendar.DAY_OF_MONTH, qtdeAcrescimo);
		int diaDaSemana = gc.get(GregorianCalendar.DAY_OF_WEEK);

		switch (diaDaSemana) {
		case 1:
			labelDiaDaSemana = "Domingo";
			break;
		case 2:
			labelDiaDaSemana = "Segunda";
			break;
		case 3:
			labelDiaDaSemana = "Terça";
			break;
		case 4:
			labelDiaDaSemana = "Quarta";
			break;
		case 5:
			labelDiaDaSemana = "Quinta";
			break;
		case 6:
			labelDiaDaSemana = "Sexta";
			break;
		case 7:
			labelDiaDaSemana = "Sábado";
			break;

		default:
			break;
		}
		return labelDiaDaSemana;
	}

	private static Document configuraEFazRequest(String url, int pagina)
			throws IOException {
		Document doc = null;

		switch (alternaHeader) {
		case 0:
			doc = Jsoup
					.connect(url + "/?page=" + pagina)
					.header("User-Agent",
							"User-Agent: Mozilla/5.0 (X11; Linux x86_64; rv:12.0) Gecko/20100101 Firefox/12.0")
					.header("Accept",
							"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
					.header("Content-Type", "text/html; charset=UTF-8")
					.header("Keep-Alive", "115").get();
			alternaHeader++;
			break;
		case 1:
			doc = Jsoup
					.connect(url + "/?page=" + pagina)
					.header("User-Agent",
							"User-Agent:  Mozilla/5.0 (Macintosh; U; Intel Mac OS X; en) AppleWebKit/418.9 (KHTML, like Gecko) Safari/419.3")
					.header("Accept",
							"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
					.header("Content-Type", "text/html; charset=UTF-8")
					.header("Keep-Alive", "115").get();
			alternaHeader++;
			break;
		case 2:
			doc = Jsoup
					.connect(url + "/?page=" + pagina)

					.header("User-Agent",
							"User-Agent:  Opera/9.27 (Windows NT 5.1; U; en)")
					.header("Accept",
							"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
					.header("Content-Type", "text/html; charset=UTF-8")
					.header("Keep-Alive", "115").get();
			alternaHeader = 0;
			break;
		}
		return doc;
	}

	static void imprime(boolean text, Element... element) {
		for (Element elem : element) {
			if (text == true)
				System.out
						.println(StringEscapeUtils.unescapeHtml4(elem.text()));
			else
				System.out.println(StringEscapeUtils.unescapeHtml4(elem
						.toString()));
		}
		System.out.println("---------------------");
	}

	static void imprime(boolean text, Elements elements) {
		for (Element elem : elements) {
			if (text == true)
				System.out
						.println(StringEscapeUtils.unescapeHtml4(elem.text()));
			else
				System.out.println(StringEscapeUtils.unescapeHtml4(elem
						.toString()));
		}
		System.out.println("---------------------");
	}

	static void imprime(String... dado) {
		for (String d : dado) {
			System.out.println(StringEscapeUtils.unescapeHtml4(d));
			System.out.println("---------------------");
		}
	}

	static String formataEArmazena(Element element) {
		return StringEscapeUtils.unescapeHtml4(element.text());
	}

	// Configuracoes de Log
	private static Log log = LogFactory.getLog(JsoupUtil.class);
	private static String log4jConfigFile = "src" + File.separator + "main"
			+ File.separator + "resources" + File.separator
			+ "log4J.properties";

}
