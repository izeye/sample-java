package learningtest.com.mysema.query.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mysema.query.jpa.impl.JPAQuery;

public class QuerydslJpaTest {

	private EntityManagerFactory entityManagerFactory;

	@Before
	public void setUp() {
		entityManagerFactory = Persistence
				.createEntityManagerFactory("com.mysema.query.jpa");
	}

	@After
	public void tearDown() {
		entityManagerFactory.close();
	}

	@Test
	public void test() {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(new Event("Our very first event!", new Date()));
		entityManager.persist(new Event("A follow up event", new Date()));
		entityManager.getTransaction().commit();

		QEvent event = QEvent.event;
		JPAQuery query = new JPAQuery(entityManager);
		List<Event> list = query.from(event).list(event);
		System.out.println(list);

		entityManager.close();
	}
}
