package org.jsp.sum.config;

import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.jsp.sum.entity.User;
import org.jsp.sum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optional = repository.findByEmail(username);
		if(optional.isEmpty())
			throw new RuntimeException("Invalid Email, Unable to find User");
		User user = optional.get();
		return user;
	}

}
