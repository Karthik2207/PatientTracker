package com.tracker.patienttracker.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tracker.patienttracker.exception.DoctorNotFoundException;
import com.tracker.patienttracker.model.Doctor;
import com.tracker.patienttracker.repository.DoctorRepository;

@Service
public class DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;
	
	public Doctor getDoctor(int doctorId) {
		Optional<Doctor> optional = doctorRepository.findById(doctorId);
		 if(!optional.isPresent()) {
				throw new DoctorNotFoundException();
			}
		 Doctor doctor =optional.get();
		 if(doctor.getUser().getApproved()==1)			 
			 return doctor;
		 else throw new DoctorNotFoundException();
	}
	
	public List<Doctor> getAllDoctors() {
		
		List<Doctor> doctors =  doctorRepository.findAll();
		
		return doctors.stream()
					  .filter(object -> object.getUser().getApproved()==1)
					  .collect(Collectors.toList());
	}
}
