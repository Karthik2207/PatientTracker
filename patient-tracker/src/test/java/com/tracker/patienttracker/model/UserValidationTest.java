package com.tracker.patienttracker.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tracker.patienttracker.util.DateUtil;

/*
 * @author: Burre Chandu
 * @task: validation
 */

@SpringBootTest
public class UserValidationTest {

	private static Validator validator;
	
	static {
	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	validator = factory.getValidator();
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"chandu123","ch123","        "," ","@!#$!","çhandu"})
	void testInValidPassword(String password) {
		User user =  new User();
		user.setPassword(password);
		Set<ConstraintViolation<User>> errors = validator.validateProperty(user, "password");
		assertFalse(errors.isEmpty());
	}

	
	@ParameterizedTest
	@ValueSource(strings = {"chandu@123","chANdu!123","A      @123","******","chandu`", "/\u0000\\u0a12\\uffff","@ççççççç"})
	void testValidPassword(String password) {
		User user =  new User();
		user.setPassword(password);
		Set<ConstraintViolation<User>> errors = validator.validateProperty(user, "password");
		assertTrue(errors.isEmpty());
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"John","Micheal","Rama swamy","Sharukh","Patrick O'Brian","François Hollande","Silvana Koch-Mehrin"})
	void testValidFirstName(String firstName) {
		User user =  new User();
		user.setFirstName(firstName);
		Set<ConstraintViolation<User>> errors = validator.validateProperty(user, "firstName");
		assertTrue(errors.isEmpty());
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"John1","        ","","ch@ndu","Patrick O1Brian"})
	void testInValidFirstName(String firstName) {
		User user =  new User();
		user.setFirstName(firstName);
		Set<ConstraintViolation<User>> errors = validator.validateProperty(user, "firstName");
		assertFalse(errors.isEmpty());
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"John","Micheal","Rama swamy","Sharukh","Patrick O'Brian","François Hollande","Silvana Koch-Mehrin"})
	void testValidLastName(String lastName) {
		User user =  new User();
		user.setLastName(lastName);
		Set<ConstraintViolation<User>> errors = validator.validateProperty(user, "lastName");
		assertTrue(errors.isEmpty());
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"John1","        ","","ch@ndu","Patrick O1Brian"})
	void testInValidLastName(String lastName) {
		User user =  new User();
		user.setLastName(lastName);
		Set<ConstraintViolation<User>> errors = validator.validateProperty(user, "lastName");
		assertFalse(errors.isEmpty());
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"m",""," ","ml","F","FE","M","OTHER"})
	void testInValidGender(String gender) {
		User user =  new User();
		user.setGender(gender);
		Set<ConstraintViolation<User>> errors = validator.validateProperty(user, "gender");
		assertFalse(errors.isEmpty());
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"male","female","other"})
	void testValidGender(String gender) {
		User user =  new User();
		user.setGender(gender);
		Set<ConstraintViolation<User>> errors = validator.validateProperty(user, "gender");
		assertTrue(errors.isEmpty());
	}
	
	@org.junit.jupiter.api.Test
	void testDateOfBirth() {
		User user =  new User();
		Date dob1 = DateUtil.convertToDate("04/12/1998");
		Date dob2 = DateUtil.convertToDate("04/12/2021");
		Date dob3 = new Date();
		user.setDateOfBirth(dob1);
		Set<ConstraintViolation<User>> errors = validator.validateProperty(user, "dateOfBirth");
		assertTrue(errors.isEmpty());
		user.setDateOfBirth(dob3);
	    errors = validator.validateProperty(user, "dateOfBirth");
		assertTrue(errors.isEmpty());
		user.setDateOfBirth(dob2);
	    errors = validator.validateProperty(user, "dateOfBirth");
		assertFalse(errors.isEmpty());
		
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"ROLEUSER","USER"," ","ADMIN_ROLE","PATIENTROLE",""})
	void testInValidRole(String role) {
		User user =  new User();
		user.setRole(role);
		Set<ConstraintViolation<User>> errors = validator.validateProperty(user, "role");
		assertFalse(errors.isEmpty());
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"ROLE_ADMIN","ROLE_DOCTOR","ROLE_PATIENT","ROLE_CLERK"})
	void testValidRole(String role) {
		User user =  new User();
		user.setRole(role);
		Set<ConstraintViolation<User>> errors = validator.validateProperty(user, "role");
		assertTrue(errors.isEmpty());
	}
	
}
