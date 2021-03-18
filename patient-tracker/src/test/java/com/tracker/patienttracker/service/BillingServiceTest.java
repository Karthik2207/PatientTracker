package com.tracker.patienttracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.tracker.patienttracker.model.Billing;
import com.tracker.patienttracker.model.Consultation;
import com.tracker.patienttracker.model.InPatientRecord;
import com.tracker.patienttracker.model.Patient;
import com.tracker.patienttracker.model.Prescription;
import com.tracker.patienttracker.model.TestReport;
import com.tracker.patienttracker.repository.BillingRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class BillingServiceTest {
	@MockBean
	BillingRepository billingRepository;
	
	@MockBean
	PatientService patientService;
	
	@MockBean
	PrescriptionService prescriptionService;
	
	@MockBean
	ConsultationService consultationService;
	
	@MockBean
	InPatientRecordService inPatientRecordService;
	
	@MockBean
	TestReportService testReportService;
	
	@Test
	@Order(1)
	void testCreateEmptyBillByPatientId() {
		BillingService billingService = new BillingService();
		
		Patient patient = new Patient();
		patient.setPatientId(22);
		
		when(patientService.getPatient(22)).thenReturn(patient);
		
		billingService.setPatientService(patientService);
		
		Billing billing = billingService.createEmptyBillByPatientId(22);
				
		assertNotNull(billing);
		assertEquals(22, billing.getPatient().getPatientId());
	}
	
	@Test
	@Order(2)
	void testSaveBilling() {
		BillingService billingService = new BillingService();
		
		Patient patient = new Patient();
		patient.setPatientId(22);
		
		Billing billing = new Billing();
		
		billing.setPatient(patient);
		
		when(billingRepository.save(billing)).thenReturn(billing);
		
		billingService.setBillingRepository(billingRepository);
		
		Billing billingResult = billingService.saveBilling(1002, 100, billing);
				
		assertNotNull(billingResult);
	}
	
	@Test
	@Order(3)
	void testGetAllPrescriptionsForPatientForBilling() throws Exception {
		BillingService billingService = new BillingService();
		
		when(prescriptionService.getAllPrescriptionsForPatientForBilling(21)).thenReturn(new ArrayList<Prescription>());
		
		billingService.setPrescriptionService(prescriptionService);
		
		List<Prescription> prescriptions = billingService.getAllPrescriptionsForPatientForBilling(21);
				
		assertNotNull(prescriptions);
	}
	
	@Test
	@Order(4)
	void testGetConsultationsPendingBillingByPatientId() {
		BillingService billingService = new BillingService();
		
		when(consultationService.getConsultationsPendingBillingByPatientId(21)).thenReturn(new ArrayList<Consultation>());
		
		billingService.setConsultationService(consultationService);
		
		List<Consultation> consultations = billingService.getConsultationsPendingBillingByPatientId(21);
				
		assertNotNull(consultations);
	}
	
	@Test
	@Order(5)
	void testGetInPatientRecordByPatientId() {
		BillingService billingService = new BillingService();
		
		when(inPatientRecordService.getInPatientRecordByPatientId(21)).thenReturn(new ArrayList<InPatientRecord>());
		
		billingService.setInPatientRecordService(inPatientRecordService);
		
		List<InPatientRecord> inPatientRecords = billingService.getInPatientRecordByPatientId(21);
				
		assertNotNull(inPatientRecords);
	}
	
	@Test
	@Order(6)
	void testGetPendingBillingTestReportsByPatientId() {
		BillingService billingService = new BillingService();
		
		when(testReportService.getPendingBillingTestReportsByPatientId(21)).thenReturn(new ArrayList<TestReport>());
		
		billingService.setTestReportService(testReportService);
		
		List<TestReport> testReports = billingService.getPendingBillingTestReportsByPatientId(21);
				
		assertNotNull(testReports);
	}
}
