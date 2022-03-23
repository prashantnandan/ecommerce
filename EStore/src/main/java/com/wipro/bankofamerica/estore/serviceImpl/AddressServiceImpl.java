package com.wipro.bankofamerica.estore.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.bankofamerica.estore.model.Address;
import com.wipro.bankofamerica.estore.repository.AddressRepository;
import com.wipro.bankofamerica.estore.service.AddressService;

/**
 *
 * @author Jeevan
 */
@Service
public class AddressServiceImpl implements AddressService {

	private static final Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);

	@Autowired
	private AddressRepository repo;

	@Override
	public Address saveAddress(Address address) {
		logger.info("Address Service Implementation : saveAddress() method");
		return repo.save(address);
	}
}
