package com.tracker.patienttracker.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.tracker.patienttracker.model.Medicine;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace =Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MedicineRepositoryTest {
	@Autowired
	MedicineRepository medicineRepository;
	
	@Test
	@Order(1)
	public void testFindByMedicineName() {
		Medicine medicine1 = new Medicine();
		medicine1.setMedicineName("Combiflame");
		medicine1.setMedicineCost(84.2);
		Medicine medicine2 = medicineRepository.findByMedicineName("Combiflame");
		assertEquals(medicine1.getMedicineCost(), medicine2.getMedicineCost(), 0.001);
	}
}
