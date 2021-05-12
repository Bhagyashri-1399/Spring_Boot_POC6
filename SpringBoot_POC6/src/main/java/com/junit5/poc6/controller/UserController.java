package com.junit5.poc6.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.junit5.poc6.bean.AuthenticationBean;
import com.junit5.poc6.dto.Response;
import com.junit5.poc6.model.User;
import com.junit5.poc6.repository.UseRepository;
import com.junit5.poc6.service.UserService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class UserController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    UserService userService;
	
	@Autowired
	UseRepository repository;
	
	@GetMapping(path = "/basicauth")
	public AuthenticationBean helloWorldBean() {
		return new AuthenticationBean("You are authenticated");
	}
	
	@GetMapping("/users")    //maps http get req
	public List<User> getUsers() {
		logger.info("Getting all users...");
		return userService.getUsers();
	}
	
	@RequestMapping("/users/{id}")
	public Optional<User> getUser(@PathVariable Long id) {
		logger.info("Getting users by their id...");
		return userService.getUser(id);
	}
	
	/*
	 * @PostMapping("/users") public Response addUser(@RequestBody User user) {
	 * userService.addUser(user); return new Response(user.getId() + " inserted",
	 * Boolean.TRUE); }
	 */
	@PostMapping("/users")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		 user.validateFirstName();
		 user.validateLastName();
		 user.validatePhoneNumber();
		 logger.info("Adding users...");
		userService.addUser(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/users/update/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id,@RequestBody User user) {
		logger.info("Updating user as per given id...");
		userService.updateUser(id,user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/users/{id}")
	public String deleteUser(@PathVariable Long id) {
		logger.info("Deleting user as per given id...");
		userService.deleteUser(id);
		//return new ResponseEntity<User>(HttpStatus.OK);
		return "User deleted with id:"+id;
	}
	
	@GetMapping(value = "/users/find/{firstName}")
	public List<User> findByFirstName(@PathVariable String firstName) {
		logger.info("Searching user as per their first name..");
		return userService.findByFirstName(firstName);
	}
	
	@GetMapping(value = "/users/getZipcode/{zipcode}")
	public List<User> findByZipCode(@PathVariable String zipcode) {
		logger.info("Searching user as per their Zip code..");
		return userService.findByZipCode(zipcode);
	}
	
	
	
	

}
