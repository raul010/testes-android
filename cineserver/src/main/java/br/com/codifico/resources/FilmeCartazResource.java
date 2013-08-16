package br.com.codifico.resources;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.codifico.dao.interfaces.CinemaDao;
import br.com.codifico.model.Cinema;

@Path("/filme")
public class FilmeCartazResource {
	@PersistenceContext
	EntityManager em;
	@Autowired
	CinemaDao cinemaDao;
	
	@GET
	@Produces("application/json")
	public Cinema getTudo() {
		System.err.println(em);
		System.err.println(cinemaDao);
		Cinema cinema = cinemaDao.find(new Integer(50));
		return cinema;
	}
}
