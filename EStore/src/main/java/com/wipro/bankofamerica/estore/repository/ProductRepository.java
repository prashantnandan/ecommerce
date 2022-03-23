package com.wipro.bankofamerica.estore.repository;

import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.wipro.bankofamerica.estore.model.Product;
/**
*
* @author Jeevan-DAO Layer
*/
@Repository
public interface ProductRepository extends CrudRepository<Product, Serializable>{
	
	public Product findById(Integer Id);

}
