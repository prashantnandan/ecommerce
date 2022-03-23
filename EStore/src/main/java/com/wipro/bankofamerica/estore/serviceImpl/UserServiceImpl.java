package com.wipro.bankofamerica.estore.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wipro.bankofamerica.estore.exception.EStoreException;
import com.wipro.bankofamerica.estore.model.FourDigitNumber;
import com.wipro.bankofamerica.estore.model.User;
import com.wipro.bankofamerica.estore.repository.FourDigitNumberRepository;
import com.wipro.bankofamerica.estore.repository.UserRepository;
import com.wipro.bankofamerica.estore.service.UserService;

/**
 *
 * @author Jeevan
 */
@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FourDigitNumberRepository fourDigitNumberRepository;

	@Override
	public User loginUser(String username, String password) {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new EStoreException("You entered incorrect username.");
		} else {
			if (user.getUsername().equals(username) && user.getUserpwd().equals(password)) {
				return user;
			}
			throw new EStoreException("You entered incorrect password.");
		}
	}

	@Override
	@Transactional
	public User saveUser(User user) {
		logger.info("User Service Implementation : saveUser() method");
		return userRepository.save(user);
	}

	@Override
	public List<User> getListByCity(String city) {
		List<User> user = userRepository.findByCity(city);
		logger.info("User Service Implementation : getListByCity() method");
		return user;
	}

	@Override
	public FourDigitNumber saveFourDigitNumber(FourDigitNumber fourDigitNumber) {
		logger.info("User Service Implementation : saveUser() method");
		return fourDigitNumberRepository.save(fourDigitNumber);
	}
}
