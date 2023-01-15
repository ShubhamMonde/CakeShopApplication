package com.sheryians.major.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.sheryians.major.model.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	Optional<User> findUserByEmail(String email);

}
