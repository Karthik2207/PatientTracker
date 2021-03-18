package com.tracker.patienttracker.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
/*
 * @author: Burre Chandu (883619)
 * 
 */
@SpringBootTest
public class PatientTests {

	@Test
	public void testSetPatientId() throws NoSuchFieldException, IllegalAccessException  {
		
        final Patient patient = new Patient();
        patient.setPatientId(111);
        final Field field = patient.getClass().getDeclaredField("patientId");
        field.setAccessible(true);
        assertEquals("Setter passed", field.get(patient), 111);
        assertNotEquals("Setter failed", field.get(patient),121);
	}
	
	@Test
	public void testGetPatientId() throws NoSuchFieldException, IllegalAccessException  {
		
        final Patient patient = new Patient();
        final Field field = patient.getClass().getDeclaredField("patientId");
        field.setAccessible(true);
        field.set(patient, 111);
        assertEquals("Getter passed",patient.getPatientId(), 111);
        assertNotEquals("Getter failed",patient.getPatientId(), 121);
	}
	
	@Test
	public void testSetBloodGroup() throws NoSuchFieldException, IllegalAccessException  {
		
        final Patient patient = new Patient();
        patient.setBloodGroup("O+");
        final Field field = patient.getClass().getDeclaredField("bloodGroup");
        field.setAccessible(true);
        assertEquals("Setter passed", field.get(patient), "O+");
        assertNotEquals("Setter failed", field.get(patient), "O-");
	}
	
	@Test
	public void testGetBloodGroup() throws NoSuchFieldException, IllegalAccessException  {
		
        final Patient patient = new Patient();
        final Field field = patient.getClass().getDeclaredField("bloodGroup");
        field.setAccessible(true);
        field.set(patient, "O+");
        assertEquals("Getter passed",patient.getBloodGroup(), "O+");
        assertNotEquals("Getter failed",patient.getBloodGroup(), "O-");
	}
}
