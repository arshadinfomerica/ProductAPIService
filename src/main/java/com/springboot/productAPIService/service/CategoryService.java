package com.springboot.productAPIService.service;

import java.util.List;

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
	
	public List<CategoryDto> getAllCategory(){
		return categoryRepo.findAll().stream().map(CategoryMapper::toCategoryDto).toList();
		
	}
	
	public CategoryDto getById(Long id) {
		Category cat= categoryRepo.findById(id).orElseThrow(()-> new RuntimeException("Category Id is not found"));
		return CategoryMapper.toCategoryDto(cat);
	}
	
	public String deleteCategory(Long id) {
		Category cat= categoryRepo.findById(id).orElseThrow(()-> new RuntimeException("Category Id is not found"));
		categoryRepo.deleteById(id);
		return "Category "+id+" has been deleted successfully";
		
	}

}
