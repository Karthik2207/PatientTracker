package com.tracker.patienttracker.repository;


import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

import com.tracker.patienttracker.model.Treatment;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace =Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TreatmentRepositoryTest {

	@Autowired
	private TreatmentRepository treatmentRepository;
	
	@Test
	@Order(1)
	public void testUpdateTreatments() {
		
		Optional<Treatment> opTreatment = treatmentRepository.findById(42);
		Treatment treatment = opTreatment.get();
		treatment.setTreatmentCost(920);
		Set<Treatment> treatments = new HashSet<>();
		treatments.add(treatment);
		List<Treatment> savedTreatments = treatmentRepository.saveAll(treatments);
		assertEquals(savedTreatments.get(0).getTreatmentCost(), treatment.getTreatmentCost());
	}
	

	@Test
	@Order(2)
	public void testTreatmentsDetails() {
		Optional<Treatment> opTreatment = treatmentRepository.findById(42);
		Treatment treatment = opTreatment.get();
		assertNotNull(treatment);
	}

	@Test
	@Order(3)
	public void testDietDetails() {
		Optional<Treatment> opTreatment = treatmentRepository.findById(41);
		Treatment treatment = opTreatment.get();
		assertNotNull(treatment.getDietExcerciseDescription());
	}
	
}
