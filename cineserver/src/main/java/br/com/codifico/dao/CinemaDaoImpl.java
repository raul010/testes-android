package br.com.codifico.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.codifico.dao.interfaces.CinemaDao;
import br.com.codifico.model.Cinema;
import br.com.codifico.model.Filme;
import br.com.codifico.model.element.CinemaElement;

@Repository
public class CinemaDaoImpl extends GenericDaoImpl<Cinema> implements CinemaDao {
	@PersistenceContext
	protected EntityManager em;
	
	/**
	 * Recupera elementos do tipo Cinema recebido como parametro
	 * @param Class<? extends Cinema>
	 */
	@Override
	@Transactional
	public List<? extends Cinema> findAll(Class<? extends Cinema> clazz) {
		String jpql = null;
		TypedQuery<? extends Cinema> query = null;
		List<? extends Cinema> cinemas = null;
		
		if(clazz.getName().equals(Cinema.class.getName())) {
			//Nao ha necessidade de implementar ainda.
			
		} else if (clazz.getName().equals(CinemaElement.class.getName())){
			jpql = "SELECT DISTINCT(c) FROM CinemaElement c LEFT JOIN FETCH c.filmes";
			query = em.createQuery(jpql, CinemaElement.class);
			cinemas = query.getResultList();
		
			//Para serializar em JSON deve inicializar a Cinema.filme.cinemas, porem 
			//nao e necessario inicializar com os dados da base para esta apresentacao
			for (Cinema cinema : cinemas) {
				List<Filme> filmes = cinema.getListaDeFilmes();
				for(Filme filme : filmes) {
					filme.setCinemas(new ArrayList<Cinema>());
				}
			}
		} else {
			throw new RuntimeException("Recebeu como parametro outra classe filha de Cinema");
		}
		
		return cinemas;
	}
}
