package com.tracker.patienttracker.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tracker.patienttracker.model.Billing;
import com.tracker.patienttracker.model.Consultation;
import com.tracker.patienttracker.model.InPatientRecord;
import com.tracker.patienttracker.model.Patient;
import com.tracker.patienttracker.model.Prescription;
import com.tracker.patienttracker.model.TestReport;
import com.tracker.patienttracker.model.User;
import com.tracker.patienttracker.repository.BillingRepository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Service
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillingService {
	@Autowired
	BillingRepository billingRepository;
	
	@Autowired
	PatientService patientService;
	
	@Autowired
	PrescriptionService prescriptionService;
	
	@Autowired
	ConsultationService consultationService;
	
	@Autowired
	InPatientRecordService inPatientRecordService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	TestReportService testReportService;
	
	public Billing createEmptyBillByPatientId(int patientId) {
		Billing billing = new Billing();
		
		Patient patient = patientService.getPatient(patientId);
		
		billing.setPatient(patient);
		
		return billing;
	}
	
	public Billing saveBilling(int patientId, int userId, Billing billing) {
		Patient patient = patientService.getPatient(patientId);
		billing.setPatient(patient);
		System.out.println(patient);
		
		billing.setTimestamp(LocalDateTime.now());
		billing.setDueDate(LocalDateTime.now().plusDays(30));
		
		User user = userService.getUserByUserId(userId);
		billing.setUser(user);
		
		if(billing.getPrescription() != null) {
			prescriptionService.updatePrescription(billing.getPrescription());
			
			billing.setPrescription(null);
		}
		
		Set<Consultation> cons = billing.getConsultations();
		Set<TestReport> testRs = billing.getTestReports();
		
		if(billing.getInPatientRecord() != null) {
			if(billing.getInPatientRecord().isRoomChargesBilled() || billing.getInPatientRecord().isNursingChargesBilled()) {
				InPatientRecord inPatientRecord =  inPatientRecordService.updateInPatientRecordBilling(billing.getInPatientRecord());
			}
		}
		
		billing.setInPatientRecord(null);
		billing.setConsultations(null);
		billing.setTestReports(null);
		
		Billing billingRes = billingRepository.save(billing);
		
		if(cons != null) {
			for(Consultation consultation: cons) {
				consultation.setBill(billingRes);
				consultationService.save(consultation);
			}
		}
		
		if(testRs != null) {
			for(TestReport testReport: testRs) {
				testReportService.updateTestReport(testReport);
			}
		}
		
		return billingRes;
	}
	
	public List<Prescription> getAllPrescriptionsForPatientForBilling(int patientId) throws Exception {
		return prescriptionService.getAllPrescriptionsForPatientForBilling(patientId);
	}
	
	public List<Consultation> getConsultationsPendingBillingByPatientId(int patientId) {
		return consultationService.getConsultationsPendingBillingByPatientId(patientId);
	}
	
	public List<InPatientRecord> getInPatientRecordByPatientId(int patientId) {
		return inPatientRecordService.getInPatientRecordByPatientId(patientId);
	}
	
	public List<TestReport> getPendingBillingTestReportsByPatientId(int patientId) {
		return testReportService.getPendingBillingTestReportsByPatientId(patientId);
	}
}
