package com.springboot.productAPIService.service;



import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.productAPIService.dto.ProductDto;
import com.springboot.productAPIService.entity.Category;
import com.springboot.productAPIService.entity.Product;
import com.springboot.productAPIService.mapper.ProductMapper;
import com.springboot.productAPIService.repository.CategoryRepository;
import com.springboot.productAPIService.repository.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {
	
	private ProductRepository productRepo;
	private CategoryRepository categoryRepo;
	
	public ProductDto createProduct(ProductDto productdto) {
		Category category=categoryRepo.findById(productdto.getCategoryId()).orElseThrow(()-> new RuntimeException("Category not found "));
		Product product= ProductMapper.toProductEntity(productdto, category);
		product=productRepo.save(product);
		return ProductMapper.toProductDto(product);	
	}
	
	public List<ProductDto> getAllProduct(){
		return productRepo.findAll().stream().map(ProductMapper::toProductDto).toList();
		}
	
	public ProductDto getById(Long id) {
		Product product=productRepo.findById(id).orElseThrow(()->new RuntimeException("Product Not Found"));
		return ProductMapper.toProductDto(product);
	}
	
	public ProductDto updateById(Long id,ProductDto productdto) {
		Product product=productRepo.findById(id).orElseThrow(()->new RuntimeException("Product Not Found"));
		Category category=categoryRepo.findById(productdto.getCategoryId()).orElseThrow(()->new RuntimeException("Category Not Found"));
		
		product.setProductName(productdto.getProductName());
		product.setDescription(productdto.getDescription());
		product.setPrice(productdto.getPrice());
		product.setCategory(category);
		productRepo.save(product);
		return ProductMapper.toProductDto(product);
		
	}
	
	public String deleteproduct(Long id) {
		Product product= productRepo.findById(id).orElseThrow(()-> new RuntimeException("Product Id is not found"));
		productRepo.deleteById(id);
		return "Product "+id+" has been deleted successfully";
		
	}

}
