package com.junit5.poc6.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.junit5.poc6.controller.UserController;
import com.junit5.poc6.model.User;
import com.junit5.poc6.repository.UseRepository;
import com.junit5.poc6.service.UserService;


@WebMvcTest(value=UserController.class, secure=false)
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UseRepository repo;
	
	@MockBean
	private UserService service;
	
	ObjectMapper om = new ObjectMapper();
	
	public static List<User> setUp() {
		User user = new User();
		user.setId(1L);
		user.setFirstName("steve");
		user.setLastName("huj");
		user.setPhoneNumber("8909990000");
		user.setEmail("st@gmail.com");
		user.setCity("ujhujhni");
		user.setCountry("India");
		user.setState("yhbjh");
		user.setZipCode("79898");
		
		User user1 = new User();
		user1.setId(2L);
		user1.setFirstName("Mathew");
		user1.setLastName("uhhu");
		user1.setPhoneNumber("8909990000");
		user1.setEmail("mw@gmail.com");
		user1.setCity("ujhujhni");
		user1.setCountry("India");
		user1.setState("yhbjh");
		user1.setZipCode("89899");
		
		List<User> listUser = new ArrayList<User>();
		listUser.add(user);
		listUser.add(user1);
		
		return listUser;
	}
	
	@Test
	public void addUserTest() throws Exception {               //add user test method
		Mockito.when(service.addUser(UserControllerTest.setUp().get(0))).thenReturn(1L);   // return particular value when particular method is called
		String payload = om.writeValueAsString(UserControllerTest.setUp().get(0));        // Method that can be used to serialize any Java value asa String
		MvcResult result = mockMvc
				.perform(post("/api/v1/users").content(payload).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated()).andReturn();
		int status = result.getResponse().getStatus();
		assertEquals(201, status);
		
	}
	
	@Test
	public void getAllUsersTest() throws Exception {         //get all user test method
		List<User> lst = UserControllerTest.setUp();
		String response = om.writeValueAsString(lst);
		Mockito.when(service.getUsers()).thenReturn(lst);
		MvcResult result = mockMvc.perform(get("/api/v1/users")
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String userRes = result.getResponse().getContentAsString();
		assertEquals(response, userRes);
				
	}
	
	@Test
	public void updateUserTest() throws Exception {
		Mockito.when(service.updateUser(UserControllerTest.setUp().get(1).getId(),UserControllerTest.setUp().get(1))).thenReturn(UserControllerTest.setUp().get(1));
		String payload = om.writeValueAsString(UserControllerTest.setUp().get(1));
		MvcResult result = mockMvc
				.perform(put("/api/v1/users/update/1").content(payload).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		int status = result.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void getUserbyIdTest() throws Exception {
		String response=om.writeValueAsString(UserControllerTest.setUp().get(0));
		Mockito.when(service.getUser(1L)).thenReturn(java.util.Optional.of(UserControllerTest.setUp().get(0)));
		MvcResult result = mockMvc.perform(get("/api/v1/users/1").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		int response1 = result.getResponse().getStatus();
		assertEquals(200, response1);
		
	}
	
	@Test
	public void deleteUserTest() throws Exception {
		
		MvcResult result = mockMvc.perform(delete("/api/v1/users/1").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		int response = result.getResponse().getStatus();
		assertEquals(200, response);
		//assertEquals("User deleted with id:1", result.getResponse().getContentAsString());
		
	}
	
	@Test
	public void searchByNameTest() throws Exception {
		User user2 = new User();
		user2.setId(4L);
		user2.setFirstName("Shreya");
		user2.setLastName("wrt");
		user2.setPhoneNumber("8877112200");
		user2.setEmail("sh@gmail.com");
		user2.setCity("Pune");
		user2.setCountry("India");
		user2.setState("M.H");
		user2.setZipCode("411108");
		List<User> list = new ArrayList<User>();
		list.add(user2);
		
		String response = om.writeValueAsString(list);
		Mockito.when(service.findByFirstName("Shreya")).thenReturn(list);
		MvcResult result = mockMvc
				.perform(get("/api/v1/users/find/Shreya").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}
	
	@Test
	public void searchByZipCodeTest() throws Exception {
		User user2 = new User();
		user2.setId(5L);
		user2.setFirstName("Sneha");
		user2.setLastName("Kdr");
		user2.setPhoneNumber("8877190823");
		user2.setEmail("sk@gmail.com");
		user2.setCity("Dadar");
		user2.setCountry("India");
		user2.setState("M.H");
		user2.setZipCode("411093");
		
		User user3 = new User();
		user3.setId(6L);
		user3.setFirstName("Neha");
		user3.setLastName("Kmr");
		user3.setPhoneNumber("8877134567");
		user3.setEmail("nk@gmail.com");
		user3.setCity("Thane");
		user3.setCountry("India");
		user3.setState("M.H");
		user3.setZipCode("409456");
		List<User> list = new ArrayList<User>();
		list.add(user2);
		list.add(user3);
		
		String response = om.writeValueAsString(list);
		Mockito.when(service.findByZipCode("409456")).thenReturn(list);
		MvcResult result = mockMvc
				.perform(get("/api/v1/users/getZipcode/409456").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}
	
	
	@Test
	@DisplayName("Should Not Create User When First Name is Null")
	public void shouldThrowRuntimeExceptionWhenFirstNameIsNull() {
		User user3 = new User();
		user3.setFirstName(null);
		user3.setLastName("xyz");
		user3.setPhoneNumber("7688990011");
		user3.setEmail("xyz@gmail.com");
		user3.setCity("Mumbai");
		user3.setState("M.H");
		user3.setCountry("India");
		user3.setZipCode("410003");
		Assertions.assertThrows(RuntimeException.class, () ->{
			user3.validateFirstName();
			service.addUser(user3);
		});
	}
	
	@Test
	@DisplayName("Should Not Create User When Last Name is Null")
	public void shouldThrowRuntimeExceptionWhenLastNameIsNull() {
		User user3 = new User();
		user3.setFirstName("Purvi");
		user3.setLastName(null);
		user3.setPhoneNumber("7688990011");
		user3.setEmail("xyz@gmail.com");
		user3.setCity("Mumbai");
		user3.setState("M.H");
		user3.setCountry("India");
		user3.setZipCode("410003");
		Assertions.assertThrows(RuntimeException.class, () ->{
			user3.validateLastName();
			service.addUser(user3);
		});
	}
	
	@Test
	@DisplayName("Should Not Create User When Phone Number is Null")
	public void shouldThrowRuntimeExceptionWhenPhoneNumberIsNull() {
		User user3 = new User();
		user3.setFirstName("Annie");
		user3.setLastName("ank");
		user3.setPhoneNumber(null);
		user3.setEmail("ann@gmail.com");
		user3.setCity("Mumbai");
		user3.setState("M.H");
		user3.setCountry("India");
		user3.setZipCode("410003");
		Assertions.assertThrows(RuntimeException.class, () ->{
			user3.validatePhoneNumber();
			service.addUser(user3);
		});
	}

}
