package learningtest.org.hibernate.tutorial.em;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EntityManagerIllustrationTest {

	private EntityManagerFactory entityManagerFactory;

	@Before
	public void setUp() {
		entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.tutorial.jpa");
	}

	@After
	public void tearDown() {
		entityManagerFactory.close();
	}

	@Test
	public void testBasicUsage() {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(new Event("Our very first event!", new Date()));
		entityManager.persist(new Event("A follow up event", new Date()));
		entityManager.getTransaction().commit();
		entityManager.close();

		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Event> result = entityManager.createQuery(
				"from learningtest.org.hibernate.tutorial.em.Event",
				Event.class).getResultList();
		for (Event event : result) {
			System.out.println("Event (" + event.getDate() + ") : "
					+ event.getTitle());
		}
		entityManager.getTransaction().commit();
		entityManager.close();
	}

}
