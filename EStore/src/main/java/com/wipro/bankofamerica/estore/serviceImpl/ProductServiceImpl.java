package com.wipro.bankofamerica.estore.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wipro.bankofamerica.estore.exception.EStoreException;
import com.wipro.bankofamerica.estore.model.Product;
import com.wipro.bankofamerica.estore.repository.ProductRepository;
import com.wipro.bankofamerica.estore.service.ProductService;

/**
*
* @author Jeevan
*/
@Service
public class ProductServiceImpl implements ProductService{

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	private ProductRepository repo; //injecting repo object into productserviceimpl

	@Override
	public Iterable<Product> getAllProduct() {
		logger.info("Product Service Implementation : getAllProduct() method");
		return repo.findAll(); //to get the all list
	}

	@Override
	@Transactional
	public Product saveProduct(Product product) {
		logger.info("Product Service Implementation : saveProduct() method");
		return repo.save(product); //repo---db
	}

	@Override
	public Product getProductById(Integer Id) {
		logger.info("Product Service Implementation : getProductById() method");
		Product product=repo.findById(Id);//null
		if(product==null) {  //null==null
			throw new EStoreException("Product id "+Id+" incorrect..");
		}
		return product;
	}

	@Override
	public void deleteProduct(Integer id) {
		logger.info("Product Service Implementation : deleteProduct() method");
		repo.deleteById(id);
	}

}
