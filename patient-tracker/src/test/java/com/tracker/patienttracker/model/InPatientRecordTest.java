package com.tracker.patienttracker.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tracker.patienttracker.util.DateUtil;

@SpringBootTest
public class InPatientRecordTest {
	@Autowired
	private Validator validator;
	
	@Before
	public void setUp() {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}
	
	@Test
	public void validateInPatientRecordPositiveTest() {
		final InPatientRecord inPatientRecord = new InPatientRecord();
		inPatientRecord.setPatient(new Patient());
		inPatientRecord.setAdmissionDate(DateUtil.convertToDate("01/01/2000"));
		inPatientRecord.setRoom(new Room());
		inPatientRecord.setNursingChargesBilled(false);
		inPatientRecord.setNursingChargesPaid(false);
		inPatientRecord.setRoomChargesBilled(false);
		inPatientRecord.setRoomChargesPaid(false);
		Set<ConstraintViolation<InPatientRecord>> violations = validator.validate(inPatientRecord);
		assertTrue(violations.isEmpty());
	}
	
	@Test
	public void validateInPatientRecordFailureTest1() {
		final InPatientRecord inPatientRecord = new InPatientRecord();
		inPatientRecord.setAdmissionDate(DateUtil.convertToDate("01/01/2000"));
		inPatientRecord.setRoom(new Room());
		inPatientRecord.setNursingChargesBilled(false);
		inPatientRecord.setNursingChargesPaid(false);
		inPatientRecord.setRoomChargesBilled(false);
		inPatientRecord.setRoomChargesPaid(false);
		Set<ConstraintViolation<InPatientRecord>> violations = validator.validate(inPatientRecord);
		assertFalse(violations.isEmpty());
	}
	
	@Test
	public void validateInPatientRecordFailureTest2() {
		final InPatientRecord inPatientRecord = new InPatientRecord();
		inPatientRecord.setPatient(new Patient());
		inPatientRecord.setRoom(new Room());
		inPatientRecord.setNursingChargesBilled(false);
		inPatientRecord.setNursingChargesPaid(false);
		inPatientRecord.setRoomChargesBilled(false);
		inPatientRecord.setRoomChargesPaid(false);
		Set<ConstraintViolation<InPatientRecord>> violations = validator.validate(inPatientRecord);
		assertFalse(violations.isEmpty());
	}
	
	@Test
	public void validateInPatientRecordFailureTest3() {
		final InPatientRecord inPatientRecord = new InPatientRecord();
		inPatientRecord.setPatient(new Patient());
		inPatientRecord.setAdmissionDate(DateUtil.convertToDate("01/01/2000"));
		inPatientRecord.setNursingChargesBilled(false);
		inPatientRecord.setNursingChargesPaid(false);
		inPatientRecord.setRoomChargesBilled(false);
		inPatientRecord.setRoomChargesPaid(false);
		Set<ConstraintViolation<InPatientRecord>> violations = validator.validate(inPatientRecord);
		assertFalse(violations.isEmpty());
	}
	
	@Test
	public void setInPatientRecordTest() throws NoSuchFieldException, IllegalAccessException {
		final InPatientRecord inPatientRecord = new InPatientRecord();
		inPatientRecord.setInPatientRecordId(1);
		final Field field = inPatientRecord.getClass().getDeclaredField("inPatientRecordId");
		field.setAccessible(true);
		assertEquals("Setter Pass Test", field.get(inPatientRecord), 1);
	}
	
	@Test
	public void getInPatientRecordTest() throws NoSuchFieldException, IllegalAccessException {
		final InPatientRecord inPatientRecord = new InPatientRecord();
		final Field field = inPatientRecord.getClass().getDeclaredField("inPatientRecordId");
        field.setAccessible(true);
        field.set(inPatientRecord, 1);
        assertEquals("Getter Pass Test",inPatientRecord.getInPatientRecordId(), 1);
	}
	
	@Test
	public void setAdmissionDateTest() throws NoSuchFieldException, IllegalAccessException, ParseException {
		final InPatientRecord inPatientRecord = new InPatientRecord();
		Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2000");		inPatientRecord.setAdmissionDate(date1);
		final Field field = inPatientRecord.getClass().getDeclaredField("admissionDate");
		field.setAccessible(true);
		assertEquals("Setter Pass Test", field.get(inPatientRecord), date1);
	}
	
	@Test
	public void getAdmissionDateTest() throws NoSuchFieldException, IllegalAccessException, ParseException {
		final InPatientRecord inPatientRecord = new InPatientRecord();
		Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2000");
		final Field field = inPatientRecord.getClass().getDeclaredField("admissionDate");
		field.setAccessible(true);
		field.set(inPatientRecord, date1);
		assertEquals("Getter Pass Test", inPatientRecord.getAdmissionDate(), date1);
	}
	
	@Test
	public void setDischargeDateTest() throws NoSuchFieldException, IllegalAccessException, ParseException {
		final InPatientRecord inPatientRecord = new InPatientRecord();
		Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2000");
		inPatientRecord.setDischargeDate(date1);
		final Field field = inPatientRecord.getClass().getDeclaredField("dischargeDate");
		field.setAccessible(true);
		assertEquals("Setter Pass Test", field.get(inPatientRecord), date1);
	}
	
	@Test
	public void getDischargeDateTest() throws NoSuchFieldException, IllegalAccessException, ParseException {
		final InPatientRecord inPatientRecord = new InPatientRecord();
		Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2000");
		final Field field = inPatientRecord.getClass().getDeclaredField("dischargeDate");
		field.setAccessible(true);
		field.set(inPatientRecord, date1);
		assertEquals("Getter Pass Test", inPatientRecord.getDischargeDate(), date1);
	}

}
