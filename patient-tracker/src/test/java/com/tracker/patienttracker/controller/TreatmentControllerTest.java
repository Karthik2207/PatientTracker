package com.tracker.patienttracker.controller;

import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tracker.patienttracker.model.PatientRecord;
import com.tracker.patienttracker.model.Treatment;
import com.tracker.patienttracker.security.JwtUtil;
import com.tracker.patienttracker.service.CustomUserDetailsService;
import com.tracker.patienttracker.service.TreatmentService;

@WebMvcTest(TreatmentController.class)
public class TreatmentControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	TreatmentService treatmentService;
	
	@MockBean
	CustomUserDetailsService customUserDetailsService;
	
	@MockBean
	JwtUtil jwtUtil;
	
	@Test
	@WithMockUser(authorities = {"ROLE_DOCTOR"})
	public void getTreatmentHistoryTest() throws Exception {
		Treatment treatment1 = new Treatment(1,"Description",12.50,"DietDescription",new PatientRecord());
		Treatment treatment2 = new Treatment(2,"Description",12.50,"DietDescription",new PatientRecord());
		Set<Treatment> treatments = new HashSet<Treatment>();
		treatments.add(treatment1);
		treatments.add(treatment2);
		
		when(treatmentService.getTreatmentHistory(1)).thenReturn(treatments);
		
		mockMvc.perform(
				MockMvcRequestBuilders
				.get("/treatment/history/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	@WithMockUser(authorities = {"ROLE_DOCTOR"})
	public void updateTreatmentTest() throws Exception {
		Treatment treatment = new Treatment(1,"Description",12.50,"DietDescription",new PatientRecord());
		when(treatmentService.updateTreatment(treatment)).thenReturn(treatment);
		
		ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(treatment);
		mockMvc.perform(MockMvcRequestBuilders
						.put("/treatment/update")
						.contentType(MediaType.APPLICATION_JSON)
						.content(json)
		).andExpect(MockMvcResultMatchers.status().isOk());
	}
}
