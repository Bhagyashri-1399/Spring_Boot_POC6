package com.junit5.poc6.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.junit5.poc6.Exception.ResourceNotFoundException;
import com.junit5.poc6.model.User;
import com.junit5.poc6.repository.UseRepository;

@Service
public class UserService {
	
	@Autowired
	private UseRepository useRepository;
	
	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		useRepository.findAll().forEach(users::add);
		return users;
	}
	
	public Optional<User> getUser(Long id) {
		return useRepository.findById(id);
	}
	
	public Long addUser(User user) {
		 useRepository.save(user);
		 return user.getId();
	}
	
	public String deleteUser(Long id) {
		useRepository.deleteById(id);
		return "User deleted with id:"+id;
	}
	
	public List<User> findByFirstName(String firstName) {
		return useRepository.findByFirstName(firstName);
	}
	
	public List<User> findByZipCode(String zipcode) {
		return useRepository.findByZipCode(zipcode);
	}
	
	public User updateUser(Long id,User user) {
		return useRepository.findById(id).map(u->{
			u.setPhoneNumber(user.getPhoneNumber());
			u.setEmail(user.getEmail());
			u.setCity(user.getCity());
			u.setState(user.getState());
			u.setCountry(user.getCountry());
			u.setZipCode(user.getZipCode());
			return useRepository.save(u);
		}).orElseThrow(()->new ResourceNotFoundException("Id"+id+"not found"));
	}

}
