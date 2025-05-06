package com.springboot.productAPIService.mapper;

import com.springboot.productAPIService.dto.CategoryDto;
import com.springboot.productAPIService.entity.Category;

public class CategoryMapper {

	
	public static CategoryDto toCategoryDto(Category category) {
		
		if(category==null) {
			return null;
		}
		CategoryDto catDto=new CategoryDto();
		catDto.setId(category.getId());
		catDto.setCategoryName(category.getCategoryName());
		catDto.setProduct(category.getProduct().stream().
				map(ProductMapper::toProductDto).
				toList());
		return catDto;
		
	}
	public static Category toCategoryEntity(CategoryDto categoryDto) {
		Category category=new Category();
		category.setCategoryName(categoryDto.getCategoryName());
		return category;
	}
}
