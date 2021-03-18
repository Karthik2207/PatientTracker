package com.tracker.patienttracker.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;

public class BillingTest {

	@Test
	void getBillingIdTest()
	{
		Billing billing=new Billing();
		billing.setBillingId(1);
		assertEquals(billing.getBillingId(),1);
	}
	@Test
	void getAmountTest()
	{	double d=100.00;
		Billing billing=new Billing();
		billing.setAmount(d);
		assertEquals(billing.getAmount(),d,0);
	}
	@Test
	void getTimeStampTest()
	{	
	    LocalDateTime date = LocalDateTime.of(2014, Month.DECEMBER, 12, 1,2);
		Billing billing=new Billing();
		billing.setTimestamp(date);
		assertEquals(billing.getTimestamp(),date);
	}
	@Test
	void getDueDateTest()
	{	
	    LocalDateTime date = LocalDateTime.of(2014, Month.DECEMBER, 12, 1,2);
		Billing billing=new Billing();
		billing.setDueDate(date);
		assertEquals(billing.getDueDate(),date);
	}

	@Test
	void getPaidTest()
	{
		Billing billing=new Billing();
		billing.setPaid(true);
		assertEquals(billing.isPaid(),true);
	}
	@Test
	void allArgsConstructorTest()
	{
		Billing billing =new Billing(1, null, null, null, 0, null, null, false, null, null, null);
		String str=billing.toString();
		assertEquals(billing.toString(),str);
	}
	// Validation Tests
	
	private static Validator validator;
	static {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
     }
	
	@Test
	void amountValidatioTest() {
		Billing billing=new Billing();
		billing.setAmount(-1);
	Set<ConstraintViolation<Billing>> violations = validator.validate(billing);
	assertFalse(violations.isEmpty());

}
	@Test
	void timestampValidatioTest() {
		Billing billing=new Billing();
		billing.setTimestamp(null);
	Set<ConstraintViolation<Billing>> violations = validator.validate(billing);
	assertFalse(violations.isEmpty());

}
	@Test
	void dueDateValidatioTest() {
		Billing billing=new Billing();
		billing.setTimestamp(null);
	Set<ConstraintViolation<Billing>> violations = validator.validate(billing);
	assertFalse(violations.isEmpty());

}

}