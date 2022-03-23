package com.wipro.bankofamerica.estore.service;

import java.util.List;

import com.wipro.bankofamerica.estore.model.FourDigitNumber;
import com.wipro.bankofamerica.estore.model.User;

/**
 *
 * @author Jeevan
 */
public interface UserService {

	public User loginUser(String username, String password) throws Exception;

	public User saveUser(User user);

	public List<User> getListByCity(String city);
	
	public FourDigitNumber saveFourDigitNumber(FourDigitNumber fourDigitNumber);

}
