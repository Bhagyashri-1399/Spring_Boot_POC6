package com.junit5.poc6.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.junit5.poc6.model.User;

public interface UseRepository extends CrudRepository<User, Long>{
	List<User> findByFirstName(String firstName);
	List<User> findByZipCode(String zipCode);
}
