package cineserver;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration({
	"classpath*:META-INF/spring/datasource-context-test.xml"
	//,"classpath*:META-INF/spring/session-factory-context.xml"
	})
public class TestePersistencia {
	
	@PersistenceContext
    protected EntityManager em;


	@Test
	public void test() {
		//fail("Not yet implemented");
		System.out.println("foi");
		EntityManagerFactory factory = null;;
		try{ 
			factory = Persistence.createEntityManagerFactory("cineserver-test");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		factory.close();
	}

}
