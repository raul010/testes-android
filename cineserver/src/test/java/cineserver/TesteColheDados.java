package cineserver;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import br.com.codifico.dao.interfaces.CinemaDao;
import br.com.codifico.model.Cinema;
import br.com.codifico.util.JsoupUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@ContextConfiguration({
	"classpath*:META-INF/spring/datasource-context-test.xml"
	})
public class TesteColheDados {
	@Autowired
	JsoupUtil jsoup;
	@Autowired
	CinemaDao cinemaDao;
	
	@Test
	@Transactional
	public void test() throws IOException {
		String urlCinema = "http://www.adorocinema.com/programacao/em-torno-298363";
		List<Cinema> cinemas = jsoup.fazRequestNosDados(urlCinema);
		for (Cinema c : cinemas) {
			cinemaDao.create(c);
		}
	}

}
