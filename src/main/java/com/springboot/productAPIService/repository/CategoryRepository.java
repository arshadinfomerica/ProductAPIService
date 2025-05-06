package com.springboot.productAPIService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.productAPIService.entity.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	Optional<Category> findByCategoryName(String categoryName);

}
