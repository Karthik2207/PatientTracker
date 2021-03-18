package com.tracker.patienttracker.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/*
 * @author: Burre Chandu
 * @task: validation
 */

@SpringBootTest
public class PatientValidationTest {
	@Autowired
	private Validator validator;

	@Before
	public void setUp() {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"O positive","O+","OA+ve","AB+",""," "})
	void testInValidBloodGroup(String bloodGroup) {
		Patient patient=new Patient();
		patient.setBloodGroup(bloodGroup);
		Set<ConstraintViolation<Patient>> errors = validator.validateProperty(patient, "bloodGroup");
		assertFalse(errors.isEmpty());
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"O+ve","O-ve","A+ve","A-ve","AB+ve","AB-ve"})
	void testValidBloodGroup(String bloodGroup) {
		Patient patient=new Patient();
		patient.setBloodGroup(bloodGroup);
		Set<ConstraintViolation<Patient>> errors = validator.validateProperty(patient, "bloodGroup");
		assertTrue(errors.isEmpty());
	}
}
