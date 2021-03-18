package com.tracker.patienttracker.model;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class MedicineTest {
	@Autowired
	Medicine medicine;
	
	@Test
	@Order(1)
	public void testGetMedicineId(){
		medicine.setMedicineId(1);
		
		assertEquals(1, medicine.getMedicineId());
	}
	
	@Test
	@Order(2)
	public void testSetMedicineId(){
		medicine.setMedicineName("Paracetamol");
		
		assertEquals(1, medicine.getMedicineId());
	}
	
	@Test
	@Order(3)
	public void testGetMedicineName(){
		medicine.setMedicineName("Paracetamol");
		
		assertEquals("Paracetamol", medicine.getMedicineName());
	}
	
	@Test
	@Order(4)
	public void testSetMedicineName(){
		medicine.setMedicineName("Paracetamol");
		
		assertEquals("Paracetamol", medicine.getMedicineName());
	}
	
	@Test
	@Order(5)
	public void testGetMedicineCost(){
		medicine.setMedicineCost(100.00);
		
		assertEquals(100.00, medicine.getMedicineCost());
	}
	
	@Test
	@Order(6)
	public void testSetMedicineCost(){
		medicine.setMedicineCost(100.00);
		
		assertEquals(100.00, medicine.getMedicineCost());
	}
	
	@Test
	@Order(7)
	public void testToString(){
		String expectedString = "Medicine(medicineId=1, medicineName=Paracetamol, medicineCost=100.00)";
		
		assertEquals(expectedString, medicine.toString());
	}
}
