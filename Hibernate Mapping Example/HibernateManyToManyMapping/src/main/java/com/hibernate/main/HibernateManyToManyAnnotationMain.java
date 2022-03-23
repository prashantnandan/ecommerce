package com.hibernate.main;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hibernate.model.Cart;
import com.hibernate.model.Product;
import com.hibernate.util.HibernateAnnotationUtil;
/**
 * @author Jeevan
*/
public class HibernateManyToManyAnnotationMain {

	public static void main(String[] args) {
		Product product1 = new Product();
		product1.setProductName("mobile");
		product1.setProductDescription("this is desc");
		
		Product product2 = new Product();
		product2.setProductName("laptop");
		product2.setProductDescription("this is desc");
		
		Set<Product> set1 = new HashSet<Product>();
		set1.add(product1); 
		set1.add(product2);
		
		Cart cart1 = new Cart();
		cart1.setName("test");	
		
		Cart cart2 = new Cart();
		cart2.setName("demo");	
		
		Set<Cart> set2= new HashSet<Cart>();
		set2.add(cart1);
		set2.add(cart2);
		
		cart1.setProduct(set1);
		cart2.setProduct(set1);
		
		SessionFactory sessionFactory = null;
		try{
		sessionFactory = HibernateAnnotationUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.save(cart1);
		session.save(cart2);
		System.out.println("Before committing transaction");
		tx.commit();
		sessionFactory.close();
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(sessionFactory != null && !sessionFactory.isClosed()) sessionFactory.close();
		}
	}

}
