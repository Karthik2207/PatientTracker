package com.tracker.patienttracker.model;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PatientRecordTest {

	private PatientRecord obj=new PatientRecord();
	
	@Test
	public void testSetrecordId() throws NoSuchFieldException, IllegalAccessException{
		obj.setRecordId(1);
		assertEquals(obj.getRecordId(),1);
	}
	@Test
	public void testGetrecordId() throws NoSuchFieldException, IllegalAccessException{
		obj.setRecordId(1);
		assertEquals(obj.getRecordId(),1);
	}
	
	/*@Test
	public void testSetdoctorId() throws NoSuchFieldException, IllegalAccessException{
		obj.setDoctorId(1);
		final Field field =obj.getClass().getDeclaredField("doctorId");
		field.setAccessible(true);
		assertEquals("Setter Method for DoctorId Passed", field.get(obj),1);
	}
	@Test
	public void testGetdoctorId() throws NoSuchFieldException, IllegalAccessException{
		final Field field =obj.getClass().getDeclaredField("doctorId");
		field.setAccessible(true);
		field.set(obj, 1);
		assertEquals("Getter Method for doctorId Passed", obj.getDoctorId(),1);
	}
	
	@Test
	public void testSettreatmentId() throws NoSuchFieldException, IllegalAccessException{
		obj.setTreatmentId(1);
		final Field field =obj.getClass().getDeclaredField("treatmentId");
		field.setAccessible(true);
		assertEquals("Setter Method for treatmentId Passed", field.get(obj),1);
	}
	@Test
	public void testGettreatmentId() throws NoSuchFieldException, IllegalAccessException{
		final Field field =obj.getClass().getDeclaredField("treatmentId");
		field.setAccessible(true);
		field.set(obj, 1);
		assertEquals("Getter Method for doctorId Passed", obj.getTreatmentId(),1);
	}
	
	@Test
	public void testSettestId() throws NoSuchFieldException, IllegalAccessException{
		obj.setTestId(1);
		final Field field =obj.getClass().getDeclaredField("testId");
		field.setAccessible(true);
		assertEquals("Setter Method for testId Passed", field.get(obj),1);
	}
	@Test
	public void testGettestId() throws NoSuchFieldException, IllegalAccessException{
		final Field field =obj.getClass().getDeclaredField("testId");
		field.setAccessible(true);
		field.set(obj, 1);
		assertEquals("Getter Method for testId Passed", obj.getTestId(),1);
	}
	
	@Test
	public void testSetpatientId() throws NoSuchFieldException, IllegalAccessException{
		obj.setPatientId(1);
		final Field field =obj.getClass().getDeclaredField("patientId");
		field.setAccessible(true);
		assertEquals("Setter Method for patientId Passed", field.get(obj),1);
	}
	@Test
	public void testGetpatientId() throws NoSuchFieldException, IllegalAccessException{
		final Field field =obj.getClass().getDeclaredField("patientId");
		field.setAccessible(true);
		field.set(obj, 1);
		assertEquals("Getter Method for patientId Passed", obj.getPatientId(),1);
	}
	
	@Test
	public void testSetprescriptionId() throws NoSuchFieldException, IllegalAccessException{
		obj.setPrescriptionId(1);
		final Field field =obj.getClass().getDeclaredField("prescriptionId");
		field.setAccessible(true);
		assertEquals("Setter Method for prescriptionId Passed", field.get(obj),1);
	}
	@Test
	public void testGetprescriptionId() throws NoSuchFieldException, IllegalAccessException{
		final Field field =obj.getClass().getDeclaredField("prescriptionId");
		field.setAccessible(true);
		field.set(obj, 1);
		assertEquals("Getter Method for prescriptionId Passed", obj.getPrescriptionId(),1);
	}
	*/

}
