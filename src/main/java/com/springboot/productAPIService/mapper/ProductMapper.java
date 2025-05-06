package com.springboot.productAPIService.mapper;

import com.springboot.productAPIService.dto.ProductDto;
import com.springboot.productAPIService.entity.Category;
import com.springboot.productAPIService.entity.Product;

public class ProductMapper {
	
	
	public static ProductDto toProductDto(Product product) {
		return new ProductDto(
				
				product.getId(),
				product.getProductName(),
				product.getDescription(),
				product.getPrice(),
				product.getCategory().getId()
				);
				
		
	}
	
	
	public static Product toProductEntity(ProductDto productDto,Category category) {
		Product product=new Product();
		product.setProductName(productDto.getProductName());
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getPrice());
		product.setCategory(category);
		return product;
		
		
		
	}

}
