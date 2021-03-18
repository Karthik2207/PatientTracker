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

import com.tracker.patienttracker.security.JwtUtil;
import com.tracker.patienttracker.service.CustomUserDetailsService;
import com.tracker.patienttracker.service.TestService;

@WebMvcTest(TestController.class)
public class TestControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	TestService testService;
	
	@MockBean
	CustomUserDetailsService customUserDetailsService;
	
	@MockBean
	JwtUtil jwtUtil;
	
	@Test
	@WithMockUser(authorities = {"ROLE_DOCTOR"})
	public void getTestListTest() throws Exception {
		com.tracker.patienttracker.model.Test test1 = new com.tracker.patienttracker.model.Test(1,"Blood Pressure Test",12.50,"120/80");
		com.tracker.patienttracker.model.Test test2 = new com.tracker.patienttracker.model.Test(2,"Blood Suger Test",16.00,"99 mg");
		Set<com.tracker.patienttracker.model.Test> tests = new HashSet<com.tracker.patienttracker.model.Test>();
		tests.add(test1);
		tests.add(test2);
		
		when(testService.getTestList(1)).thenReturn(tests);
		
		mockMvc.perform(MockMvcRequestBuilders
						.get("/test/details/1")
		).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	@WithMockUser(authorities = {"ROLE_DOCTOR"})
	public void getTestByNameTest() throws Exception {
		com.tracker.patienttracker.model.Test test1 = new com.tracker.patienttracker.model.Test(1,"Blood Pressure Test",12.50,"120/80");
		com.tracker.patienttracker.model.Test test2 = new com.tracker.patienttracker.model.Test(2,"Blood Suger Test",16.00,"99 mg");
		Set<com.tracker.patienttracker.model.Test> tests = new HashSet<com.tracker.patienttracker.model.Test>();
		tests.add(test1);
		tests.add(test2);
		
		when(testService.getTestListContainingName("Blood")).thenReturn(tests);
		
		mockMvc.perform(MockMvcRequestBuilders
						.get("/test/blood")
		).andExpect(MockMvcResultMatchers.status().isOk());
	}

}
