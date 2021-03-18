package com.tracker.patienttracker.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tracker.patienttracker.validator.ValidPassword;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Component
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@NotEmpty(message = "The firstname cannot be empty")
	@NotBlank(message = "The firstname cannot be blank or whitespace")
	@Pattern(regexp = "^[\\p{L} .'-]+$", message="Enter a Valid first Name ")
	private String firstName;
	
	@NotEmpty(message = "The lastname cannot be empty")
	@NotBlank(message = "The lastname cannot be blank or whitespace")
	@Pattern(regexp = "^[\\p{L} .'-]+$", message="Enter a Valid last Name ")
	private String lastName;
	
	@NotEmpty(message = "Gender cannot be empty")
	@NotBlank(message = "Gender cannot be blank or whitespace")
	@Pattern(regexp = "^(male|female|other)$", message="male|female|other is only allowed ")
	private String gender;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	@NotNull(message = "The date of birth cannot be null")
	@PastOrPresent(message = "The date of birth is invalid")
	private Date dateOfBirth;
	
	@Size(min=10,max=10)
	@NotEmpty(message = "Contact no cannot be empty")
	@NotBlank(message = "Contact no cannot be blank or whitespace")
	@Pattern(regexp="^[1-9][0-9]{9}$", message="Enter a Valid Contact Number ")
	private String contactNo;
	
	@ValidPassword(message="Invalid password")
	@NotEmpty(message = "Password no cannot be empty")
	@Length(min=6, message="Password should be atleast six characters")
	private String password;
	
	@NotEmpty(message = "Address cannot be empty")
	@NotBlank(message = "Address cannot be blank or whitespace")
	private String address;
	
	@Pattern(regexp="^ROLE_(PATIENT|DOCTOR|ADMIN|CLERK)$", message="invalid role ")
	@NotEmpty(message = "role cannot be empty")
	@NotBlank(message = "role cannot be blank or whitespace")
	private String role;

	@Min(value=-1)
  	@Max(value=1)
	private int approved;
}

