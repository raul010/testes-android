package cineserver;

import java.io.IOException;

import org.junit.Test;

import br.com.codifico.util.JsoupUtil;

public class TesteRecolheDados {

	@Test
	public void test() throws IOException {
		String urlCinema = "http://www.adorocinema.com/programacao/em-torno-298363";
		JsoupUtil.fazRequestNosDados(urlCinema);
	}

}
