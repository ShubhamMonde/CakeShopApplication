package com.sheryians.major.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sheryians.major.model.Category;
import com.sheryians.major.model.Product;
import com.sheryians.major.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	
	//method To add new product
	public void addProduct(Product product) {
		productRepository.save(product);	
	}
		
	//to display all details  stored in database
	public Iterable<Product> getAllProduct(){
		return productRepository.findAll();
	}
	
	//method to delete category
	public void removeProductById(Long id) {
		productRepository.deleteById(id);
	}
	
	//To fetch id whose update request is came
	public Optional<Product> getProductById(Long id){//we use Optional here because requested id will be present or not
		return productRepository.findById(id);
	}
	
	//this is for user section
	//it provide list of product of same category
	//जेव्हा आपण flipcart वर Samsung चा filter लावतो तेव्हा आपल्याला फक्त Samsung चे mobile दिसतात.
	public Iterable<Product> getAllProductByCategoryId(int id){
		return productRepository.findAllByCategory_Id(id);
	}

}
