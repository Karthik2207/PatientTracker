package com.tracker.patienttracker.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tracker.patienttracker.model.Patient;
import com.tracker.patienttracker.model.User;
import com.tracker.patienttracker.repository.PatientRepository;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import org.springframework.stereotype.Service;

import com.tracker.patienttracker.exception.PatientNotFoundException;
import com.tracker.patienttracker.model.Patient;
import com.tracker.patienttracker.repository.PatientRepository;

@Service
@Configurable(preConstruction = true, autowire = Autowire.BY_NAME)  
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;
	
	public Patient getPatient(int patientId) {
		 Optional<Patient> optional = patientRepository.findById(patientId);
		 if(!optional.isPresent()) {
				throw new PatientNotFoundException();
			}
		 Patient patient = optional.get();
		 return patient;
	}
	
	public Patient getPatientDetails(int patientId){
		return patientRepository.findById(patientId).get();
	}
	
	@Transactional
	public void addPatient(Patient patient)
	{
		patientRepository.save(patient);
	}
	@Transactional
	public void updatePatient(Patient patient,int patientId)
	{
		Patient p=patientRepository.findById(patientId).get();
		p.setBloodGroup(patient.getBloodGroup());
		p.setUser(patient.getUser());
		patientRepository.save(p);
	}
	public List<Patient> getPatientList()
	{
		return patientRepository.findAll();
	}
}
