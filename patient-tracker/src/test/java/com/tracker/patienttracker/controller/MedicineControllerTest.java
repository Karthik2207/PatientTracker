package com.tracker.patienttracker.controller;

import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.tracker.patienttracker.model.Medicine;
import com.tracker.patienttracker.security.JwtUtil;
import com.tracker.patienttracker.service.CustomUserDetailsService;
import com.tracker.patienttracker.service.MedicineService;

@WebMvcTest(MedicineController.class)
public class MedicineControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	MedicineService medicineService;
	
	@MockBean
	CustomUserDetailsService customUserDetailsService;
	
	@MockBean
	JwtUtil jwtUtil;
	
	@Test
	@WithMockUser(authorities = {"ROLE_DOCTOR"})
	public void getMedicinesTest() throws Exception {
		Medicine medicine1 = new Medicine(1,"Atenolol",12.50);
		Medicine medicine2 = new Medicine(2,"Paracitomol",22.00);
		Set<Medicine> medicines = new HashSet<Medicine>();
		medicines.add(medicine1);
		medicines.add(medicine2);
		
		when(medicineService.getMedicinesContainingName("ol")).thenReturn(medicines);
		
		mockMvc.perform(MockMvcRequestBuilders
						.get("/medicine/ol")
		).andExpect(MockMvcResultMatchers.status().isOk());
		
	}
}
