package com.sheryians.major.repository;

import org.springframework.data.repository.CrudRepository;


import com.sheryians.major.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{

	Iterable<Product> findAllByCategory_Id(int id);

}
