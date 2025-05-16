package com.springboot.productAPIService.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.springboot.productAPIService.dto.ProductDto;
import com.springboot.productAPIService.service.ProductService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	private ProductService productService;
	
	@PreAuthorize("hasRole('ROLE_SELLER')")
	@PostMapping
	public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
		return  new ResponseEntity<>(productService.createProduct(productDto),HttpStatus.CREATED);
	}
	
	
	@GetMapping
	public List<ProductDto> getAllProduct(){
		return productService.getAllProduct();
	}
	
	@GetMapping("/{id}")
	public ProductDto getById(@PathVariable Long id) {
		return productService.getById(id);
	}
	
	@PreAuthorize("hasRole('ROLE_SELLER')")
	@PutMapping("/{id}")
	public ProductDto updateProductByid(@PathVariable Long id,@RequestBody ProductDto productDto) {
		return productService.updateById(id, productDto);
	}
	
	@PreAuthorize("hasRole('ROLE_SELLER')")
	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable Long id) {
		return productService.deleteproduct(id);
	}

}
