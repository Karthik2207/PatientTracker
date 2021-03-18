package com.tracker.patienttracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tracker.patienttracker.model.Consultation;
import com.tracker.patienttracker.repository.ConsultationRepository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Service
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationService {
	@Autowired
	ConsultationRepository consultationRepository;
	
	public List<Consultation> getConsultationsPendingBillingByPatientId(int patientId) {
		return consultationRepository.getConsultationsPendingBillingByPatientId(patientId);
	}

	public void save(Consultation consult) {
		consultationRepository.save(consult);
	}
}
