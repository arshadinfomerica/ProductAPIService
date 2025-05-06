package com.springboot.productAPIService.service;

import org.springframework.stereotype.Service;

import com.springboot.productAPIService.dto.CategoryDto;
import com.springboot.productAPIService.entity.Category;
import com.springboot.productAPIService.mapper.CategoryMapper;
import com.springboot.productAPIService.repository.CategoryRepository;

@Service
public class CategoryService {
	
	private CategoryRepository categoryRepo;
	
	public CategoryService(CategoryRepository categoryRepo) {
		this.categoryRepo=categoryRepo;
	}
	
	
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cat=CategoryMapper.toCategoryEntity(categoryDto);
		cat=categoryRepo.save(cat);
		return CategoryMapper.toCategoryDto(cat);
		
	}

}
