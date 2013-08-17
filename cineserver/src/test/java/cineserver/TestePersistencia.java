package cineserver;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import br.com.codifico.dao.interfaces.CinemaDao;
import br.com.codifico.dao.interfaces.CinemaElementDao;
import br.com.codifico.model.Cinema;
import br.com.codifico.model.element.CinemaElement;


@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@ContextConfiguration(locations={"classpath:datasource-context-test.xml"})
public class TestePersistencia {
	@Autowired
	Cinema cinemaTest;
	
	@Resource(type=CinemaDao.class)
	CinemaDao cinemaDao;
	
	@Resource(type=CinemaElementDao.class)
	CinemaElementDao cinemaElementDao;
	
	//Ficou obsoleto mas é um bom exemplo de Anotações com Herança
	//CinemaDao pai dos Repositorios, CinemaDaoImpl e CinemaElementDaoImpl
//	@Resource(name="CinemaElement")
//	CinemaDao cinemaElementDao;
	
	@PersistenceContext
    EntityManager em;
	
	Cinema cinema;

	//@Test
	public void test() {
		//fail("Not yet implemented");
		System.err.println("em --> " + em);
		System.err.println("@Autowired [Cinema] --> " + cinemaTest);
		System.err.println("@Autowired [CinemaDao] --> " + cinemaDao);
		//System.err.println("@Autowired [CinemaElementDao] --> " + cinemaElementDao);
	}
	
	//@Test
	@Transactional
	public void recupera() {
		
		cinema = cinemaDao.find(100);
		
		System.err.println(cinema.getNome());
	}
	@Test
	@Transactional
	public void recuperaTudo() {
		List<Cinema> cinemas = cinemaDao.findAll();
		for (Cinema cinema : cinemas) {
			System.out.println(cinema.getNome());
		}
	}

}
