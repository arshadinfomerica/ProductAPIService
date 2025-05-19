package com.springboot.productAPIService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.productAPIService.entity.UsersTable;


public interface UserRepository extends JpaRepository<UsersTable, Long> {
	
	Optional<UsersTable> findByUsername(String username);

}
