package org.jsp.sum.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// it is a secured controller

@RestController
public class UserController {

	@PreAuthorize("hasAuthority('USER')")
	@GetMapping("/hi")
	public String hi() {
		System.out.println("Hiiiiiiiiiiii");
		return "Hello From Spring Boot Application";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/hello")
	public String hello() {
		System.out.println("Hello");
		return "Hi From Spring Boot Application";
	}

}
