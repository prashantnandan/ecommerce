package com.wipro.bankofamerica.estore.repository;

import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wipro.bankofamerica.estore.model.Employee;
import com.wipro.bankofamerica.estore.model.Product;
/**
*
* @author Jeevan
*/
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Serializable>{
	public Employee findById(Integer Id);
}
