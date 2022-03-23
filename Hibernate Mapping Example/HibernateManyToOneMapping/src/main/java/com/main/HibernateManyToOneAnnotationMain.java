package com.main;
/*
 * @author Jeevan
 */
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.model.Address;
import com.model.Employee;
import com.util.HibernateAnnotationUtil;

public class HibernateManyToOneAnnotationMain {

	public static void main(String[] args) {

		Employee e1 = new Employee();
		e1.setName("ashutosh");
		e1.setEmail("ashu@gmail.com");

		Employee e2 = new Employee();
		e2.setName("Sagar");
		e2.setEmail("Sagar@gmail.com");

		Address address1 = new Address();
		address1.setAddressLine1("kothrud");
		address1.setCity("Pune");
		address1.setState("Maharashtra");
		address1.setCountry("India");
		address1.setPincode(411035);

		e1.setAddress(address1);
		e2.setAddress(address1);

		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			// Get Session
			sessionFactory = HibernateAnnotationUtil.getSessionFactory();
			session = sessionFactory.getCurrentSession();
			System.out.println("Session created");
			// start transaction
			tx = session.beginTransaction();
			// Save the Model object
			session.persist(e1);
			session.persist(e2);
			// Commit transaction
			tx.commit();
		} catch (Exception e) {
			System.out.println("Exception occured. " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (!sessionFactory.isClosed()) {
				System.out.println("Closing SessionFactory");
				sessionFactory.close();
			}
		}
	}
}
