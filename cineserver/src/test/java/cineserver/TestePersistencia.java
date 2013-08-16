package cineserver;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import br.com.codifico.model.Cinema;


@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@ContextConfiguration({
	"classpath*:META-INF/spring/datasource-context-test.xml"
	})
public class TestePersistencia {
	@Autowired
	Cinema cinema;
	
	@PersistenceContext
    EntityManager em;


	@Test
	public void test() {
		//fail("Not yet implemented");
		System.err.println("em --> " + em);
		System.err.println("@Autowired [Cinema] --> " + cinema);
	}

}
