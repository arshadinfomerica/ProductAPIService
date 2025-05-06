package com.springboot.productAPIService.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.productAPIService.dto.CategoryDto;
import com.springboot.productAPIService.service.CategoryService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/category")
@AllArgsConstructor
public class CategoryController {
	
	
	private CategoryService categoryService;
	
	@PostMapping
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
		return new ResponseEntity<>(categoryService.createCategory(categoryDto),HttpStatus.CREATED);
	}
	
	@GetMapping
	public List<CategoryDto> getAllCategory(){
		return categoryService.getAllCategory();
		
	}
	
	@GetMapping("/{id}")
	public CategoryDto getById(@PathVariable Long id) {
		return categoryService.getById(id);
	}
	@DeleteMapping("/{id}")
	public String deleteCategory(@PathVariable Long id) {
		return categoryService.deleteCategory(id);
		
	}

}
