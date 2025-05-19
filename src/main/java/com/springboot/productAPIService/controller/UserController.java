package com.springboot.productAPIService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.productAPIService.dto.UserDto;
import com.springboot.productAPIService.entity.UsersTable;
import com.springboot.productAPIService.security.JwtUtil;
import com.springboot.productAPIService.service.MyUsersDetailsService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private MyUsersDetailsService userService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@PostMapping("/register")
	public ResponseEntity<UsersTable> createUser(@RequestBody UsersTable usersDetails) {
		usersDetails.setPassword(encoder.encode(usersDetails.getPassword()));
		return new ResponseEntity<>(userService.createUser(usersDetails),HttpStatus.CREATED);
	}
	
	
	@PostMapping("/login")
	public String login(@RequestBody UserDto user) {
		Authentication auth= authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(auth);
		List<String> roles=auth.getAuthorities()
		.stream().map(GrantedAuthority::getAuthority).toList();
		
		UserDetails userDetails= userService.loadUserByUsername(user.getUsername());
		
		return jwtUtil.generateToken(userDetails.getUsername(), roles);
		
	}

}
