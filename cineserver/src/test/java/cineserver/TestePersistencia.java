package cineserver;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import br.com.codifico.dao.interfaces.CinemaDao;
import br.com.codifico.model.Cinema;


@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@ContextConfiguration({
	"classpath*:META-INF/spring/datasource-context-test.xml"
	})
public class TestePersistencia {
	@Autowired
	Cinema cinemaTest;
	
	@Resource(name="Cinema")
	CinemaDao cinemaDao;
	
	//Ficou obsoleto mas é um bom exemplo de Anotações com Herança
	//CinemaDao pai dos Repositorios, CinemaDaoImpl e CinemaElementDaoImpl
	@Resource(name="CinemaElement")
	CinemaDao cinemaElementDao;
	
	@PersistenceContext
    EntityManager em;
	
	Cinema cinema;

	@Test
	public void test() {
		//fail("Not yet implemented");
		System.err.println("em --> " + em);
		System.err.println("@Autowired [Cinema] --> " + cinemaTest);
		System.err.println("@Autowired [CinemaDao] --> " + cinemaDao);
		System.err.println("@Autowired [CinemaElementDao] --> " + cinemaElementDao);
	}
	
	//@Test
	public void recupera() {
		
		cinema = cinemaDao.find(50);
		
		System.out.println(cinema.getNome());
	}

}
