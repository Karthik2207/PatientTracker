package com.tracker.patienttracker.controller;

import static org.mockito.Mockito.when;
import java.util.ArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;
import com.tracker.patienttracker.model.Patient;
import com.tracker.patienttracker.service.PatientService;

@WebMvcTest
public class PatientControllerTest {

	@InjectMocks
	PatientController patientController;
	@Mock
	PatientService patientService;
	
	 @Test
	 void testGetAllPatientList() {
		
		when(patientService.getPatientList()).thenReturn(new ArrayList<Patient>());
		 ResponseEntity<Object> responseEntity=(ResponseEntity<Object>) patientController.getAllPatientList();
		 assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
	     assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");
	 }
}
