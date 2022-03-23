package com.hibernate.main;

import java.util.HashSet;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.hibernate.model.Product;
import com.hibernate.model.User;
import com.hibernate.util.HibernateAnnotationUtil;
/**
 * @author Jeevan
 */
public class HibernateOneToManyAnnotationMain {

	public static void main(String[] args) {

		User user = new User();
		
		Product product1 = new Product("J10", "mobile","this is desc", "2","300", user);
		Product product2 = new Product("J11", "camera","this is desc", "1","500", user);
		
		Set<Product> productSet = new HashSet<Product>();
		productSet.add(product1); 
		productSet.add(product2);
		user.setProduct(productSet);
		user.setName("sandip");
		user.setEmail("sandip@gmail.com");
		
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try{
		//Get Session
		sessionFactory = HibernateAnnotationUtil.getSessionFactory();
		session = sessionFactory.getCurrentSession();
		System.out.println("Session created");
		//start transaction
		tx = session.beginTransaction();
		//Save the Model object
		session.save(user);
		session.save(product1);
		session.save(product2);
		//Commit transaction
		tx.commit();
		System.out.println("User ID="+user.getId());
		System.out.println("Product1 ID="+product1.getId()+", Foreign Key User ID="+product1.getUser().getId());
		System.out.println("Product2 ID="+product2.getId()+", Foreign Key User ID="+product1.getUser().getId());
		
		}catch(Exception e){
			System.out.println("Exception occured. "+e.getMessage());
			e.printStackTrace();
		}finally{
			if(!sessionFactory.isClosed()){
				System.out.println("Closing SessionFactory");
				sessionFactory.close();
			}
		}
	}

}
