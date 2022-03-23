package com.wipro.bankofamerica.estore.service;

import com.wipro.bankofamerica.estore.model.Employee;

/**
 *
 * @author Jeevan
 */
public interface EmployeeService {

	public Employee saveEmployee(Employee employee);
	
	public Employee getEmployee(Integer id);


}
