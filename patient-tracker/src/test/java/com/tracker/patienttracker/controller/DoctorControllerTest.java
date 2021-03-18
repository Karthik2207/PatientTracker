package com.tracker.patienttracker.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.tracker.patienttracker.model.Doctor;
import com.tracker.patienttracker.model.User;
import com.tracker.patienttracker.security.JwtUtil;
import com.tracker.patienttracker.service.CustomUserDetailsService;
import com.tracker.patienttracker.service.DoctorService;

@WebMvcTest(DoctorController.class)
public class DoctorControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	DoctorService doctorService;
	
	@MockBean
	CustomUserDetailsService customUserDetailsService;
	
	@MockBean
	JwtUtil jwtUtil;
	
	@Test
	@WithMockUser(authorities = {"ROLE_ADMIN, ROLE_PATIENT"})
	public void getAllDoctorsTest() throws Exception {
		User user1 = new User();
		user1.setUserId(1);
		user1.setApproved(1);
		User user2 = new User();
		user2.setUserId(2);
		user2.setApproved(1);
		Doctor doctor1 = new Doctor(1,"MD","Heart",500.00,user1);
		Doctor doctor2 = new Doctor(2,"MD","Heart",400.00,user2);
		List<Doctor> doctors = new ArrayList<Doctor>();
		doctors.add(doctor1);
		doctors.add(doctor2);
		
		when(doctorService.getAllDoctors()).thenReturn(doctors);
		
		mockMvc.perform(MockMvcRequestBuilders
						.get("/doctors")
		).andExpect(MockMvcResultMatchers.status().isOk());
	}

}
