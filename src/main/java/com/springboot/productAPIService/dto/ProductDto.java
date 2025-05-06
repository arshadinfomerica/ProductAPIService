package com.springboot.productAPIService.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
	
	private Long id;
	private String productName;
	private String description;
	private double price;
	private Long categoryId;

}
