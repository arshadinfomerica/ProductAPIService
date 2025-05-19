package com.springboot.productAPIService.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.productAPIService.entity.UsersTable;
import com.springboot.productAPIService.repository.UserRepository;
import com.springboot.productAPIService.security.UserPrincipal;

@Service
public class MyUsersDetailsService implements UserDetailsService {
	
	
	@Autowired
	private UserRepository userRepo;
	
	
	
	public UsersTable createUser(UsersTable userDetails) {
		return userRepo.save(userDetails);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UsersTable> users=userRepo.findByUsername(username);
		if(users.isPresent()) {
			return new UserPrincipal(users.get());
		}
		else{
			 throw new UsernameNotFoundException("Username not found");
		}
	}

}
