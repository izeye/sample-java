package learningtest.org.hibernate.tutorial.hbm;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NativeApiIllustrationTest {

	private SessionFactory sessionFactory;

	@Before
	public void setUp() {
		// sessionFactory = new Configuration().configure(
		// "/learningtest/org/hibernate/tutorial/hbm/hibernate.cfg.xml")
		// .buildSessionFactory();

		Configuration configuration = new Configuration()
				.configure("/learningtest/org/hibernate/tutorial/hbm/hibernate.cfg.xml");
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
				.applySettings(configuration.getProperties())
				.buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}

	@After
	public void tearDown() {
		if (sessionFactory != null) {
			sessionFactory.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testBasicUsage() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(new Event("Our very first event!", new Date()));
		session.save(new Event("A follow up event", new Date()));
		session.getTransaction().commit();
		session.close();

		session = sessionFactory.openSession();
		session.beginTransaction();
		@SuppressWarnings("rawtypes")
		List result = session.createQuery("from Event").list();
		for (Event event : (List<Event>) result) {
			System.out.println("Event (" + event.getDate() + ") : "
					+ event.getTitle());
		}
		session.getTransaction().commit();
		session.close();
	}

}
