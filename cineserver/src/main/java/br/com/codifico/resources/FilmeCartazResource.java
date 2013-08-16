package br.com.codifico.resources;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.com.codifico.dao.interfaces.CinemaDao;
import br.com.codifico.model.Cinema;

@Path("/filme")
public class FilmeCartazResource {
	@PersistenceContext
	EntityManager em;
	@Resource(name="Cinema")
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
