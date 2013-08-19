package br.com.codifico.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonEncoding;

import br.com.codifico.dao.interfaces.CinemaDao;
import br.com.codifico.model.Cinema;

@Controller
@RequestMapping("cinemas")
public class FilmeCartazResource {

	@Resource(type=CinemaDao.class)
	CinemaDao cinemaDao;
	
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public @ResponseBody List<Cinema> getTudo() {

		List<Cinema> cinemas = cinemaDao.findAll();
		//Hibernate.initialize(cinemas);
		return cinemas;
	}
	@RequestMapping(value="{cidade}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public @ResponseBody List<Banda> getTudo(@PathVariable String cidade) {
		System.out.println(cidade);
		Banda b = new Banda();
		b.setAnoDeFormacao(1985);
		b.setId(1);
		b.setNome("Raul");
		b.setLista(new ArrayList<String>());
		Banda b2 = new Banda();
		b2.setAnoDeFormacao(1985);
		b2.setId(1);
		b2.setNome("Raul");
		List<Banda> bandas = Arrays.asList(new Banda[]{b, b2});
		
		return bandas;
	}
}
