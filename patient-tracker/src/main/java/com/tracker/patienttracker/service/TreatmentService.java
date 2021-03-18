package com.tracker.patienttracker.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tracker.patienttracker.exception.TreatmentNotFoundException;
import com.tracker.patienttracker.model.Treatment;
import com.tracker.patienttracker.repository.PatientRecordRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import com.tracker.patienttracker.model.Treatment;
import com.tracker.patienttracker.repository.TreatmentRepository;

@Service
public class TreatmentService {
	@Autowired
	TreatmentRepository treatmentRepository;
  
	@Autowired
	private PatientRecordService patientRecordService;
	
	@Transactional
	public void updateTreatments(Set<Treatment> treatments) {
		List<Treatment> updatedTreatments = treatmentRepository.saveAll(treatments);
	}

	public Treatment getTreatmentDetails( int treatmentId)
	{   
		return treatmentRepository.findById(treatmentId).get();
	}
	
	@Transactional
	public Treatment updateTreatment(Treatment treatment) {
		Optional<Treatment> treatmentCheck = treatmentRepository.findById(treatment.getTreatmentId());
		if(treatmentCheck == null)
			throw new TreatmentNotFoundException();
		return treatmentRepository.save(treatment);
	}

	public void saveTreatment(Treatment treatment) {
	
		treatmentRepository.save(treatment);
		
	}
	public Set<Treatment> getTreatmentHistory(int patientId) //can be used as diet list also
	{
		return patientRecordService.getPatientRecordForPatientId(patientId).getTreatments();
	}
}
