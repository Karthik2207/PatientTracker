 package com.tracker.patienttracker.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import com.tracker.patienttracker.model.TestReport;

public class TestReportTest {
	
	@Test
	void getTestResultIdTest()
	{
		TestReport testReport=new TestReport();
		testReport.setTestResultId(1);
		assertEquals(testReport.getTestResultId(),1);
	}
	
	@Test
	void getTestResultTest()
	{
		TestReport testReport=new TestReport();
		testReport.setTestResult("Done");
		assertEquals(testReport.getTestResult(),"Done");
	}

	@Test
	void getRequestedTest()
	{
		TestReport testReport=new TestReport();
		testReport.setRequested(false);
		assertEquals(testReport.isRequested(),false);
	}
	@Test
	void getPaidTest()
	{
		TestReport testReport=new TestReport();
		testReport.setPaid(true);
		assertEquals(testReport.isPaid(),true);
	}
	
	// Validation Tests
	private static Validator validator;
		static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testResultValidatioTest() {
    	TestReport testReport=new TestReport();
    	testReport.setTestResult("");
    	Set<ConstraintViolation<TestReport>> violations = validator.validate(testReport);
    	assertFalse(violations.isEmpty());

    }


    }
