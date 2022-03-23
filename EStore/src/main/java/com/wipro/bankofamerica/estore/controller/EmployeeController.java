package com.wipro.bankofamerica.estore.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.bankofamerica.estore.model.Address;
import com.wipro.bankofamerica.estore.model.Employee;
import com.wipro.bankofamerica.estore.model.Product;
import com.wipro.bankofamerica.estore.service.AddressService;
import com.wipro.bankofamerica.estore.service.EmployeeService;

/**
 *
 * @author Jeevan
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private AddressService addressService;

	//emp with address- employee with multiple address
	
	@PostMapping("/saveEmpAdd")
	ResponseEntity<Employee> saveEmployeeAddress(@RequestBody Employee employee) {
		Employee employees = employeeService.saveEmployee(employee);
		List<Address> add = employees.getAddress();
		for (Address address : add) {
			addressService.saveAddress(address);
		}
		logger.info("Employee Rest Controller Implementation : saveEmployeeAddress() method");
		return ResponseEntity.ok().body(employee);

	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Integer id) {
		Employee emp = employeeService.getEmployee(id);
		logger.info("Employee Rest Controller Implementation : getEmployeeById() method");
		               
		return ResponseEntity.ok().body(emp);
	}
	
	

}
