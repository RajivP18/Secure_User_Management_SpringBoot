package org.jsp.sum.controller;

import org.jsp.sum.entity.User;
import org.jsp.sum.service.UserService;
import org.jsp.sum.util.LoginCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// it is a open controller

@RestController
public class OpenController {

	@Autowired
	private UserService userService;

	@PostMapping("/create-account")
	public ResponseEntity<?> saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginCredentials credentials) {
		return userService.login(credentials);
	}

}
