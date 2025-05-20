package org.jsp.sum.service;

import java.util.Optional;

import org.jsp.sum.config.JWTUtils;
import org.jsp.sum.dao.UserDao;
import org.jsp.sum.entity.User;
import org.jsp.sum.responsestructure.ResponseStructure;
import org.jsp.sum.util.LoginCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private JWTUtils jwtUtils;
	
	@Autowired
	private AuthenticationManager manager;
	
	
	
	
	public ResponseEntity<?> saveUser(User user) {
		Optional<User> optional = userDao.findByEmail(user.getEmail());
		if(optional.isPresent())
			throw new RuntimeException("Diplication Email, Can't Create Account");
		user.setPassword(encoder.encode(user.getPassword()));
		User savedUser = userDao.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(ResponseStructure.builder().status(HttpStatus.CREATED.value()).message("User Saved Successfully").body(savedUser).build());
	}

	public ResponseEntity<?> login(LoginCredentials credentials) {
		manager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword()));
		Optional<User> optional = userDao.findByEmail(credentials.getEmail());		
		if(optional.isEmpty())
			throw new RuntimeException("Invalid Email");
		User user = optional.get();
		String token = jwtUtils.generateToken(user);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("Login Successfull").body(user).token(token).build());
	}
	
	
	
	
	
}
