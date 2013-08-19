package br.com.codifico.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.codifico.dao.interfaces.CinemaDao;
import br.com.codifico.model.Cinema;
import br.com.codifico.model.Filme;

@Repository
public class CinemaDaoImpl extends GenericDaoImpl<Cinema> implements CinemaDao {
	@PersistenceContext
	protected EntityManager em;

	@Override
	@Transactional
	public List<Cinema> findAll() {
		String jpql = "SELECT c FROM Cinema c";
		TypedQuery<Cinema> query = em.createQuery(jpql, Cinema.class);
		List<Cinema> cinemas = query.getResultList();
		for (Cinema cinema : cinemas) {
			List<Filme> filmes = cinema.getListaDeFilmes();
			for(Filme filme : filmes) {
				filme.setCinemas(new ArrayList<Cinema>());
				//Hibernate.initialize(filme.getGenero());
			}
		}
		return cinemas;
	}
}
