package com.tracker.patienttracker.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.stereotype.Component;

@Component
public class ConstraintValidation {
	
	public String validationCheck(Object obj) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Object>> violations = validator.validate(obj);
		String errors="";
		for (ConstraintViolation<Object> violation : violations) {
			   errors+=violation.getMessage()+"\n"; 
			}
			return errors;
	}

}
