package org.jsp.sum.dao;

import java.util.Optional;

import org.jsp.sum.entity.User;
import org.jsp.sum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	@Autowired
	private UserRepository repository;

	public User saveUser(User user) {
		return repository.save(user);
	}
	
	
	public Optional<User> findByEmail(String email){
		return repository.findByEmail(email);
	}
	
	
}
