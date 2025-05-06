package com.springboot.productAPIService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springboot.productAPIService.dto.CategoryDto;
import com.springboot.productAPIService.entity.Category;
import com.springboot.productAPIService.exception.CategoryAlreadyExistsException;
import com.springboot.productAPIService.exception.CategoryNotFoundException;
import com.springboot.productAPIService.mapper.CategoryMapper;
import com.springboot.productAPIService.repository.CategoryRepository;

@Service
public class CategoryService {
	
	private CategoryRepository categoryRepo;
	
	public CategoryService(CategoryRepository categoryRepo) {
		this.categoryRepo=categoryRepo;
	}
	
	
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		Optional<Category> optionalName=categoryRepo.findByCategoryName(categoryDto.getCategoryName());
		if(optionalName.isPresent()) {
			throw new CategoryAlreadyExistsException("Category "+categoryDto.getCategoryName()+" Already Exists:");
		}
		
		Category cat=CategoryMapper.toCategoryEntity(categoryDto);
		cat=categoryRepo.save(cat);
		return CategoryMapper.toCategoryDto(cat);
		
	}
	
	public List<CategoryDto> getAllCategory(){
		return categoryRepo.findAll().stream().map(CategoryMapper::toCategoryDto).toList();
		
	}
	
	public CategoryDto getById(Long id) {
		Category cat= categoryRepo.findById(id).orElseThrow(()-> new CategoryNotFoundException("Category "+id+" is not found"));
		return CategoryMapper.toCategoryDto(cat);
	}
	
	public String deleteCategory(Long id) {
		Category cat= categoryRepo.findById(id).orElseThrow(()-> new CategoryNotFoundException("Category "+id+" is not found"));
		categoryRepo.deleteById(id);
		return "Category "+id+" has been deleted successfully";
		
	}

}
