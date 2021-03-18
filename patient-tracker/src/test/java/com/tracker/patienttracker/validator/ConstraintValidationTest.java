package com.tracker.patienttracker.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tracker.patienttracker.model.Doctor;
import com.tracker.patienttracker.model.Patient;
import com.tracker.patienttracker.model.User;
import com.tracker.patienttracker.util.DateUtil;

@SpringBootTest
public class ConstraintValidationTest {
	
	@Autowired
	ConstraintValidation constraintValidation;
		
	public User userMethod() {
		User obj= new User();
		obj.setFirstName("abcdefg");
		obj.setLastName("abcdefg");
		obj.setGender("male");
		obj.setDateOfBirth(DateUtil.convertToDate("22/07/1998"));
		obj.setContactNo("1234567890");
		obj.setPassword("K@1234kask");
		obj.setAddress("akasakakas");
		obj.setRole("ROLE_PATIENT");
		return obj;
	}
	@Test
	public void validationCheckTestForUser() {
		ConstraintValidationTest obj=new ConstraintValidationTest();	
		User obj1=obj.userMethod();
		obj1.setGender("m");
		String s=constraintValidation.validationCheck(obj1);
		assertEquals("male|female|other is only allowed "+"\n", s);
	}
	
	@Test
	public void validationCheckTestForDoctor() {
		ConstraintValidationTest obj=new ConstraintValidationTest();
		Doctor obj1=new Doctor(1,"  ","abcdef",1000.0,obj.userMethod());			
		String s=constraintValidation.validationCheck(obj1);
		assertEquals("Qualification cannot be blank or whitespace"+"\n", s);
	}
	
	@Test
	public void validationCheckTestForPatient() {
		ConstraintValidationTest obj=new ConstraintValidationTest();
		Patient obj1=new Patient(1,"O",obj.userMethod());			
		String s=constraintValidation.validationCheck(obj1);
		assertEquals("BloodGroup can only be O|A|B|AB +ve|-ve"+"\n", s);
	}
}
