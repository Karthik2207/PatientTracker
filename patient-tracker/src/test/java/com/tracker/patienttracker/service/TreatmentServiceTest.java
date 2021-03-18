package com.tracker.patienttracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.tracker.patienttracker.exception.TreatmentNotFoundException;
import com.tracker.patienttracker.model.PatientRecord;
import com.tracker.patienttracker.model.Treatment;
import com.tracker.patienttracker.repository.TreatmentRepository;

@SpringBootTest
public class TreatmentServiceTest {
	
	@InjectMocks
	TreatmentService treatmentService;
	
	@Mock
	TreatmentRepository treatmentRepository;
	
	@Mock
	PatientRecordService patientRecordService;
	
	@Test
	public void updateTreatmentsTest() {
		Treatment treatment1 = new Treatment();
		treatment1.setTreatmentId(1);
		Treatment treatment2 = new Treatment();
		treatment1.setTreatmentId(2);
		Set<Treatment> treatments = new HashSet<Treatment>();
		treatments.add(treatment1);
		treatments.add(treatment2);
		List<Treatment> listTreatments = new ArrayList<Treatment>();
		listTreatments.addAll(treatments);
		when(treatmentRepository.saveAll(treatments)).thenReturn(listTreatments);
		treatmentService.updateTreatments(treatments);
		verify(treatmentRepository, times(1)).saveAll(treatments);
	}
	
	@Test
	public void getTreatmentDetailsTest() {
		Treatment treatment = new Treatment();
		treatment.setTreatmentId(1);
		Optional<Treatment> optionalTreatment = Optional.of(treatment);
 		when(treatmentRepository.findById(1)).thenReturn(optionalTreatment);
		assertEquals(treatmentService.getTreatmentDetails(1), treatment);
	}
	
	@Test
	public void updateTreatmentTest() {
		Treatment treatment = new Treatment();
		treatment.setTreatmentId(1);
		Optional<Treatment> optionalTreatment = Optional.of(treatment);
		when(treatmentRepository.findById(treatment.getTreatmentId())).thenReturn(optionalTreatment);
		when(treatmentRepository.save(treatment)).thenReturn(treatment);
		assertEquals(treatmentService.updateTreatment(treatment), treatment);
	}
	
	@Test
	public void updateTreatmentNegativeTest() {
		Treatment treatment = new Treatment();
		treatment.setTreatmentId(1);
		when(treatmentRepository.findById(treatment.getTreatmentId())).thenReturn(null);
		assertThrows(TreatmentNotFoundException.class,() -> treatmentService.updateTreatment(treatment));
	}
	
	@Test
	public void saveTreatmentTest() {
		Treatment treatment = new Treatment();
		treatment.setTreatmentId(1);
		when(treatmentRepository.save(treatment)).thenReturn(null);
		treatmentService.saveTreatment(treatment);
		verify(treatmentRepository, times(1)).save(treatment);
	}
	
	@Test
	public void getTreatmentHistoryTest() {
		Treatment treatment1 = new Treatment();
		treatment1.setTreatmentId(1);
		Treatment treatment2 = new Treatment();
		treatment1.setTreatmentId(2);
		Set<Treatment> treatments = new HashSet<Treatment>();
		treatments.add(treatment1);
		treatments.add(treatment2);
		
		PatientRecord patientRecord = new PatientRecord();
		patientRecord.setRecordId(1);
		patientRecord.setTreatments(treatments);
		
		when(patientRecordService.getPatientRecordForPatientId(1)).thenReturn(patientRecord);
		assertEquals(treatmentService.getTreatmentHistory(1), treatments);
	}

}
