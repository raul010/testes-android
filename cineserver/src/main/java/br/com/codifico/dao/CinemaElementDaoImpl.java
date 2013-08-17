package br.com.codifico.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.codifico.dao.interfaces.CinemaElementDao;
import br.com.codifico.model.Cinema;
import br.com.codifico.model.element.CinemaElement;


/**
 * Ficou obsoleto mas é um bom exemplo de Anotações com Herança
 * CinemaDao pai dos Repositorios, CinemaDaoImpl e CinemaElementDaoImpl
 * @author Raul
 *
 */
@Repository
public class CinemaElementDaoImpl extends GenericDaoImpl<CinemaElement> implements CinemaElementDao {
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional(readOnly=true)
	public List<CinemaElement> findAll() {
		String jpql = "SELECT c FROM Cinema c WHERE TYPE(c)= CinemaElement";
		TypedQuery<CinemaElement> query = em.createQuery(jpql, CinemaElement.class);
		List<CinemaElement> cinemas = query.getResultList();
		return cinemas;
	}
	
}
