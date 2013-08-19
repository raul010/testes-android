package cineserver;

import org.junit.Test;

import br.com.codifico.model.Cinema;

public class Teste {

	@Test
	public void teste() {
		Class<? extends Cinema> cinema = null;
		
		cinema = Cinema.class;
		
		if(cinema.getName().equals(Cinema.class.getName())) {
			System.out.println("igual");
			System.out.println(cinema.getName());
			System.out.println(Cinema.class.getName());
			System.out.println(cinema.toString());
			System.out.println(Cinema.class.toString());
		}
	}

}
