package com.sheryians.major.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sheryians.major.model.Category;
import com.sheryians.major.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	CategoryRepository categoryRepository;
	
	//method To add new category
	public void addCategory(Category category) {
		categoryRepository.save(category);	
	}

	//to display all details  stored in database
	public Iterable<Category> getAllCategory(){
		return categoryRepository.findAll();
	}

	//method to delete category
	public void removeCategoryById(int id) {
		categoryRepository.deleteById(id);
	}
		
	//To fetch id whose update request is came
	public Optional<Category> getCategoryById(int id){//we use Optional here because requested id will be present or not
		return categoryRepository.findById(id);
	}

}
