package com.tracker.patienttracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.tracker.patienttracker.model.PatientRecord;
import com.tracker.patienttracker.model.Prescription;
import com.tracker.patienttracker.repository.PrescriptionRepository;

@SpringBootTest
public class PrescriptionServiceTest {
	
	@InjectMocks
	private PrescriptionService prescriptionService;
	
	@Mock
	private PrescriptionRepository prescriptionRepository;
	
	@Mock
	private PatientRecordService patientRecordService;
	
//	@Mock
//	private UserService userService;
	
	@Test
	public void updatePrescriptionsTest() {
		Prescription prescription1 = new Prescription();
		prescription1.setPrescriptionId(1);
		Prescription prescription2 = new Prescription();
		prescription1.setPrescriptionId(2);
		Set<Prescription> prescriptions = new HashSet<Prescription>();
		prescriptions.add(prescription1);
		prescriptions.add(prescription2);
		List<Prescription> listPrescriptions = new ArrayList<Prescription>();
		listPrescriptions.addAll(prescriptions);
		when(prescriptionRepository.saveAll(prescriptions)).thenReturn(listPrescriptions);
		prescriptionService.updatePrescriptions(prescriptions);
		verify(prescriptionRepository, times(1)).saveAll(prescriptions);
	}
	
	@Test
	public void getAllPrescriptionsForPatientForBillingTest() throws Exception {
		
		PatientRecord patientRecord = new PatientRecord();
		patientRecord.setRecordId(31);
		Prescription prescription1 = new Prescription();
		prescription1.setPrescriptionId(1);
		Prescription prescription2 = new Prescription();
		prescription1.setPrescriptionId(2);
		Set<Prescription> prescriptions = new HashSet<Prescription>();
		prescriptions.add(prescription1);
		prescriptions.add(prescription2);
		patientRecord.setPrescriptions(prescriptions);
		when(patientRecordService.getPatientRecordForPatientId(31)).thenReturn(patientRecord);
		List<Prescription> listPrescriptions = new ArrayList<Prescription>();
		listPrescriptions.addAll(prescriptions);
		assertEquals(prescriptionService.getAllPrescriptionsForPatientForBilling(31), listPrescriptions);
	}
	
	@Test
	public void addPrescriptionTest() {
//		AuthResponse response = new AuthResponse(1, "Ram", true, "ROLE_ADMIN");
//		when(userService.getValidity("token")).thenReturn(response);
		
		Prescription prescription = new Prescription();
		prescription.setPrescriptionId(1);
		when(prescriptionRepository.save(prescription)).thenReturn(prescription);
		assertEquals(prescriptionService.addPrescription(prescription), prescription);
	}
	
	@Test
	public void updatePrescriptionTest() {
//		AuthResponse response = new AuthResponse(1, "Ram", true, "ROLE_ADMIN");
//		when(userService.getValidity("token")).thenReturn(response);
		
		Prescription prescription = new Prescription();
		prescription.setPrescriptionId(1);
		when(prescriptionRepository.save(prescription)).thenReturn(prescription);
		assertEquals(prescriptionService.updatePrescription(prescription), prescription);
	}

}
