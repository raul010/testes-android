package cineserver;

import br.com.codifico.model.Cinema;
import br.com.codifico.util.JsoupUtil;



public class Main {
	public static void main(String[] args) throws Exception {
		
		Cinema cine = new Cinema();
		
		String urlCinema = "http://www.adorocinema.com/programacao/em-torno-298363";
		JsoupUtil.fazRequestPorCinema(urlCinema);
		
	}
}

