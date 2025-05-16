package com.springboot.productAPIService.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.productAPIService.entity.UsersDetails;
import com.springboot.productAPIService.repository.UserRepository;

@Service
public class MyUsersDetailsService implements UserDetailsService {
	
	
	@Autowired
	private UserRepository userRepo;
	
	
	
	public UsersDetails createUser(UsersDetails userDetails) {
		return userRepo.save(userDetails);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UsersDetails> users=userRepo.findByUsername(username);
		if(users.isPresent()) {
			UsersDetails u=users.get();
			return User.builder()
					.username(u.getUsername())
					.password(u.getPassword())
					.roles(u.getRoles())
					.build();
		}
		else{
			 throw new UsernameNotFoundException("Username not found");
		}
	}

}
