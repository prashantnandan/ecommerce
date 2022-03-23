package com.wipro.bankofamerica.estore.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.bankofamerica.estore.model.User;
import com.wipro.bankofamerica.estore.service.UserService;

/**
 *
 * @author Jeevan
 */
@RestController
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserService userService;

	/*
	 * This method is used to login the user to the eshop system.
	 */
	@PostMapping("/login")
	public ResponseEntity<User> createUser(@RequestBody User user) throws Exception {
		String username = user.getUsername();
		String password = user.getUserpwd();
		user = userService.loginUser(username, password);
		logger.info("Login Rest Controller Implementation : createUser() method");
		return ResponseEntity.ok().body(user);

	}

	@GetMapping("/getEncodedPassword")
	public ResponseEntity<String> getPasswordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String myPassword = "mumbai";
		String encodedPassword = bCryptPasswordEncoder.encode(myPassword);
		return ResponseEntity.ok().body(encodedPassword);

	}

}
