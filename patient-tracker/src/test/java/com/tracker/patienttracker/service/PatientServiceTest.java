package com.tracker.patienttracker.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.tracker.patienttracker.model.Patient;
import com.tracker.patienttracker.model.Treatment;
import com.tracker.patienttracker.repository.PatientRepository;
@SpringBootTest
public class PatientServiceTest {
	@InjectMocks
	PatientService patientService;
	
	@Mock
	PatientRepository patientRepository;
	
	@Test
	void testGetPatient()
	{  
		Patient patient =new Patient();
		patient.setPatientId(200);
		Optional<Patient> optionalPatient = Optional.of(patient);
		when(patientRepository.findById(200)).thenReturn(optionalPatient);
		assertEquals(patientService.getPatient(200).getPatientId(),patient.getPatientId());
	}
	@Test
	void testUpdatePatient()
	{  
		Patient patient =new Patient();
		patient.setPatientId(200);
		Optional<Patient> optionalPatient = Optional.of(patient);
		when(patientRepository.findById(patient.getPatientId())).thenReturn(optionalPatient);
		when(patientRepository.save(patient)).thenReturn(patient);
		//assertNotNull(patientService.updatePatient(patient, 200));
		
	}
	@Test
	void testGetPatientList()
	{  
		Patient patient =new Patient();
		patient.setPatientId(200);
		//Optional<Patient> optionalPatient = Optional.of(patient);
		//when(patientRepository.findAll()).thenReturn(optionalPatient);
		assertEquals(patientService.getPatientList().size(),1);
	}
}
