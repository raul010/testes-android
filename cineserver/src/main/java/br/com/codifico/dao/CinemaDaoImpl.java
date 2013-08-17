package br.com.codifico.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.codifico.dao.interfaces.CinemaDao;
import br.com.codifico.model.Cinema;

@Repository
public class CinemaDaoImpl extends GenericDaoImpl<Cinema> implements CinemaDao {
	@PersistenceContext
	protected EntityManager em;

	@Override
	@Transactional(readOnly=true)
	public List<Cinema> findAll() {
		String jpql = "SELECT c FROM Cinema c";
		TypedQuery<Cinema> query = em.createQuery(jpql, Cinema.class);
		List<Cinema> cinemas = query.getResultList();
		return cinemas;
	}
}
