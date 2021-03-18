package com.tracker.patienttracker.model;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConsultationTest {

	private Consultation obj=new  Consultation();
	
	@Test
	public void testSetconsultationId(){
		obj.setConsultationId(1);
		assertEquals(obj.getConsultationId(),1);
	}
	@Test
	public void testGetconsultationId(){
		obj.setConsultationId(1);
		assertEquals(obj.getConsultationId(),1);
	}
	
	@Test
	 void testGetDoctor(){
		 Consultation obj1=new  Consultation();
		 Doctor doctor=new Doctor();
		 obj1.setDoctorId(doctor);
		 assertEquals(obj1.getDoctorId(),doctor);
	}
	@Test
	 void testGetPatient(){
		 Consultation obj1=new  Consultation();
		 Patient patient=new Patient();
		 obj1.setPatientId(patient);
		 assertEquals(obj1.getPatientId(),patient);
	}
	@Test
	 void testGetBillingt(){
		 Consultation obj1=new  Consultation();
		Billing billing =new Billing();
		 obj1.setBill(billing);
		 assertEquals(obj1.getBill(),billing);
	}
	@Test
	 void testGetPaid(){
		 Consultation obj1=new  Consultation();
		 obj1.setPaid(false);
		 assertEquals(obj1.isPaid(),false);
	}
	@Test
	 void testGetDate(){
		 Consultation obj1=new  Consultation();
		 obj1.setDate(null);
		 assertEquals(obj1.getDate(),null);
	}
	@Test
	 void testAllArgs(){
		 Consultation obj1=new  Consultation(1, null, null, null, null, false);
		String str= obj1.toString();
		 assertEquals( obj1.toString(),str);
	}
}
