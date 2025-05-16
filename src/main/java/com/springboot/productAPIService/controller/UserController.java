package com.springboot.productAPIService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.productAPIService.entity.UsersDetails;
import com.springboot.productAPIService.service.MyUsersDetailsService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private MyUsersDetailsService userService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@PostMapping("/register")
	public ResponseEntity<UsersDetails> createUser(@RequestBody UsersDetails usersDetails) {
		usersDetails.setPassword(encoder.encode(usersDetails.getPassword()));
		return new ResponseEntity<>(userService.createUser(usersDetails),HttpStatus.CREATED);
	}

}
