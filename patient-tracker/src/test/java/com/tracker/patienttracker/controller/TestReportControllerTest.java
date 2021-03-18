package com.tracker.patienttracker.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tracker.patienttracker.dto.TestResultDTO;
import com.tracker.patienttracker.model.TestReport;
import com.tracker.patienttracker.security.JwtUtil;
import com.tracker.patienttracker.service.CustomUserDetailsService;
import com.tracker.patienttracker.service.TestReportService;

@WebMvcTest(TestReportController.class)
@TestMethodOrder(OrderAnnotation.class)
class TestReportControllerTest {
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	TestReportService testReportService;
	
	@MockBean
	CustomUserDetailsService customUserDetailsService;
	@MockBean
	JwtUtil jwtUtil;
	
	@Test
	@Order(1)
	@WithMockUser(authorities = "ROLE_ADMIN")
	void testGetPendingUpdateTestReports() throws Exception {
		when(testReportService.getPendingUpdateTestReports()).thenReturn(new ArrayList<TestReport>());
		
		ResultActions actions = mockMvc.perform(get("/testreport"));
		actions.andExpect(status().isOk());
		
		actions.andExpect(jsonPath("$[0]").doesNotExist());
	}
	
	@Test
	@Order(2)
	@WithMockUser(authorities = "ROLE_ADMIN")
	void testUpdateTestResult() throws Exception {
		TestReport testReportGenerated = new TestReport();
		testReportGenerated.setTestResultId(21);
		testReportGenerated.setTestResult("Sugar level normal");
		
		when(testReportService.modifyTestReport(21, "Sugar level normal")).thenReturn(testReportGenerated);
		
		ResultActions actions = mockMvc.perform(put("/testreport/21")
				.content(asJsonString(new TestResultDTO("Sugar level normal")))
				.contentType(MediaType.APPLICATION_JSON)
			     .accept(MediaType.APPLICATION_JSON));
		actions.andExpect(status().isOk());
		
		actions.andExpect(jsonPath("$").value("Updated"));
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
