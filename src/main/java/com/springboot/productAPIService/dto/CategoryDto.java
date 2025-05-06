package com.springboot.productAPIService.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
	
	
	private Long id;
	private String categoryName;
	private List<ProductDto> product;

}
