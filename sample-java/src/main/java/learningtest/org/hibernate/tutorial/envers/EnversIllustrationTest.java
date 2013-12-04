package learningtest.org.hibernate.tutorial.envers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EnversIllustrationTest {

	private EntityManagerFactory entityManagerFactory;

	@Before
	public void setUp() {
		entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.tutorial.envers");
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
				"from learningtest.org.hibernate.tutorial.envers.Event",
				Event.class).getResultList();
		for (Event event : result) {
			System.out.println("Event (" + event.getDate() + ") : "
					+ event.getTitle());
		}
		entityManager.getTransaction().commit();
		entityManager.close();

		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Event myEvent = entityManager.find(Event.class, 2L);
		myEvent.setDate(new Date());
		myEvent.setTitle(myEvent.getTitle() + " (rescheduled)");
		entityManager.getTransaction().commit();
		entityManager.close();

		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		myEvent = entityManager.find(Event.class, 2L);
		assertEquals("A follow up event (rescheduled)", myEvent.getTitle());

		AuditReader reader = AuditReaderFactory.get(entityManager);
		Event firstRevision = reader.find(Event.class, 2L, 1);
		assertFalse(firstRevision.getTitle().equals(myEvent.getTitle()));
		assertFalse(firstRevision.getDate().equals(myEvent.getDate()));
		Event secondRevision = reader.find(Event.class, 2L, 2);
		assertTrue(secondRevision.getTitle().equals(myEvent.getTitle()));
		assertTrue(secondRevision.getDate().equals(myEvent.getDate()));
		entityManager.getTransaction().commit();
		entityManager.close();

		System.out.println(myEvent);
		System.out.println(firstRevision);
		System.out.println(secondRevision);
	}

}
