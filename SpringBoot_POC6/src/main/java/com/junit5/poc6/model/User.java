package com.junit5.poc6.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "test_user")
// Causes Lombok to generate toString(), equals(), hashCode(), getter() & setter(), and Required arguments constructor in one go.
@Data
// Causes Lombok to implement the Builder design pattern for the Pojo class.
// Usage can be seen in DefaultResidentsLoader.java -> createNewResident() method.
@Builder
// Causes Lombok to generate a constructor with no parameters.
@NoArgsConstructor
// Causes Lombok to generate a constructor with 1 parameter for each field in your class.
@AllArgsConstructor
@Component
public class User {
	
 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Size(max = 65)
	private String firstName;
	
	@Size(max = 65)
	private String lastName;
	
	@NotNull
	@Email
	@Size(max = 100)
	@Column(unique = true)
	private String email;
	
	@Size(max = 15)
	private String phoneNumber;
	
	@Size(max = 100)
	private String city;
	
	@Size(max = 100)
	private String state;
	
	@Size(max = 100)
	private String country;
	
	@Size(max = 32)
	private String zipCode;
	
	public void validateFirstName() {
		if (this.firstName == null) {
			throw new RuntimeException("First Name Cannot be null");
		}
	}
	
	public void validateLastName() {
		if (this.lastName == null) {
			throw new RuntimeException("Last Name Cannot be null");
		}
	}
	
	public void validatePhoneNumber() {
		if (this.phoneNumber.length() != 10) {
			throw new RuntimeException("Phone Number Should be 10 Digits Long");
		}
		if (!this.phoneNumber.matches("\\d+")) {
			throw new RuntimeException("Phone Number Contain only digits");
		}
	}

 
}
