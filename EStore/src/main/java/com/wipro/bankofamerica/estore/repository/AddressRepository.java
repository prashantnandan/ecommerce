package com.wipro.bankofamerica.estore.repository;

import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wipro.bankofamerica.estore.model.Address;
/**
*
* @author Jeevan
*/
@Repository
public interface AddressRepository extends CrudRepository<Address, Serializable>{
	
}
