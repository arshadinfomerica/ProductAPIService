package com.springboot.productAPIService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.productAPIService.entity.UsersDetails;


public interface UserRepository extends JpaRepository<UsersDetails, Long> {
	
	Optional<UsersDetails> findByUsername(String username);

}
