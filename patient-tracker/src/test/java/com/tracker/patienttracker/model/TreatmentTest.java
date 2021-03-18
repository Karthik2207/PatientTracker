package com.tracker.patienttracker.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TreatmentTest {
	static Validator validator;

	static {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		}
	
	private Treatment obj=new Treatment();
	
	@Test
	public void testSettreatmentId(){
		obj.setTreatmentId(1);
		assertEquals(obj.getTreatmentId(),1);
	}
	@Test
	public void testGettreatmentId(){
		obj.setTreatmentId(1);
		assertEquals(obj.getTreatmentId(),1);
	}
	
	@Test
	public void testSettreatmentDescription() throws NoSuchFieldException, IllegalAccessException{
		obj.setTreatmentDescription("Malaria Test");
		assertEquals(obj.getTreatmentDescription(),"Malaria Test");
	}
	@Test
	public void testGettreatmentDescription(){
		obj.setTreatmentDescription("Malaria Test");
		assertEquals(obj.getTreatmentDescription(),"Malaria Test");
	}
	
	@Test
	public void testSettreatmentCost(){
		obj.setTreatmentCost(2000.50);
		assertEquals(obj.getTreatmentCost(),2000.50,0.0);
	}
	@Test
	public void testGettreatmentCost() {
		obj.setTreatmentCost(2000.50);
		assertEquals(obj.getTreatmentCost(),2000.50,0.0);
	}
	
	@Test
	public void testSetdietExcerciseDescription(){
		obj.setDietExcerciseDescription("Drink Water");
		assertEquals(obj.getDietExcerciseDescription(),"Drink Water");
	}
	@Test
	public void testGetdietExcerciseDescription(){
		obj.setDietExcerciseDescription("Drink Water");
		assertEquals(obj.getDietExcerciseDescription(),"Drink Water");
	}

	@Test
	public void testValidationSucess() {
		obj.setTreatmentId(1);
		obj.setTreatmentDescription("Malaria Test");
		obj.setTreatmentCost(2000.50);
		//obj.setDietExcerciseDescription("Drink Water");
		Set<ConstraintViolation<Treatment>> violations = validator.validate(obj);
	    assertTrue(violations.isEmpty());
	}
}
