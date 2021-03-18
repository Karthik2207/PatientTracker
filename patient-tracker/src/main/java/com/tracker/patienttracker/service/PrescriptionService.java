package com.tracker.patienttracker.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tracker.patienttracker.model.Treatment;
import com.tracker.patienttracker.repository.TreatmentRepository;
import com.tracker.patienttracker.model.PatientRecord;
import com.tracker.patienttracker.model.Prescription;
import com.tracker.patienttracker.repository.PrescriptionRepository;

@Service
public class PrescriptionService {
	
	@Autowired
	private PrescriptionRepository prescriptionRepository;
	
	@Autowired
	private PatientRecordService patientRecordService;
	
//	@Autowired
//	private UserService userService;
  @Transactional
	public void updatePrescriptions(Set<Prescription> prescriptions) {
		
		List<Prescription> updatedPrescriptions = prescriptionRepository.saveAll(prescriptions);
		
	}
  
	

  @Transactional
	public List<Prescription> getAllPrescriptionsForPatientForBilling(int patientId) throws Exception{
		PatientRecord patientRecord = patientRecordService.getPatientRecordForPatientId(patientId);
		List<Prescription> prescriptions = patientRecord.getPrescriptions().stream().filter((prescription) -> !prescription.isBilled()).collect(Collectors.toList());
		return prescriptions;
	}
	
	@Transactional
	public Prescription addPrescription(Prescription prescription) {
		return prescriptionRepository.save(prescription);
	}
	
	@Transactional
	public Prescription updatePrescription(Prescription prescription) {
    return prescriptionRepository.save(prescription);
	}
	
	public Prescription getPrescriptionById(int prescriptionId) {
		return prescriptionRepository.findById(prescriptionId).get();
	}
}
