package br.com.codifico.resources;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.codifico.dao.interfaces.CinemaDao;
import br.com.codifico.model.Cinema;

@Controller
@RequestMapping("cinemas")
public class FilmeCartazResource {

	@Resource(type=CinemaDao.class)
	CinemaDao cinemaDao;

	@RequestMapping(value="{cidade}", method=RequestMethod.GET)
	@ResponseBody
	@Transactional
	public List<Cinema> getTudo(@PathVariable String cidade) {
		System.out.println(cidade);
		List<Cinema> cinemas = cinemaDao.findAll();
		return cinemas;
	}
	@RequestMapping(value="{nome/nome}", method=RequestMethod.GET)
	@ResponseBody
	//@Transactional
	public String getUm(@PathVariable String nome) {
		//model.addAttribute("movie", name);
//		System.err.println(em);
//		System.err.println(cinemaDao);
//		System.err.println(cinema);
		//Cinema cinema = cinemaDao.find(100);
		System.out.println(nome);
		return nome;
	}
}
