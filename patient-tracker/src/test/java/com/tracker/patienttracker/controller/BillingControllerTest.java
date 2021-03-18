package com.tracker.patienttracker.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.tracker.patienttracker.model.Billing;
import com.tracker.patienttracker.model.Consultation;
import com.tracker.patienttracker.model.InPatientRecord;
import com.tracker.patienttracker.model.Prescription;
import com.tracker.patienttracker.model.TestReport;
import com.tracker.patienttracker.security.JwtUtil;
import com.tracker.patienttracker.service.BillingService;
import com.tracker.patienttracker.service.CustomUserDetailsService;

@WebMvcTest(BillingController.class)
@TestMethodOrder(OrderAnnotation.class)
class BillingControllerTest {
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	BillingService billingService;
	
	//Security Beans
	@MockBean
	CustomUserDetailsService customUserDetailsService;
	
	@MockBean
	JwtUtil jwtUtil;
	
	@Test
	@Order(1)
	@WithMockUser(authorities = "ROLE_ADMIN")
	void testCreateEmptyBillByPatientId() throws Exception {
		when(billingService.createEmptyBillByPatientId(21)).thenReturn(new Billing());
		
		ResultActions actions = mockMvc.perform(get("/billing/21"));
		actions.andExpect(status().isOk());
	}
	
	@Test
	@Order(2)
	@WithMockUser(authorities = "ROLE_ADMIN")
	void testSaveBilling() throws Exception {
		Billing billing = new Billing();
		
		when(billingService.saveBilling(1002, 100, billing)).thenReturn(billing);
		
		ResultActions actions = mockMvc.perform(post("/billing")
				.content(asJsonString(billing))
				.contentType(MediaType.APPLICATION_JSON)
			     .accept(MediaType.APPLICATION_JSON));
		actions.andExpect(status().isOk());
	}
	
	@Test
	@Order(3)
	@WithMockUser(authorities = "ROLE_ADMIN")
	void testGetAllPrescriptionsForPatientForBilling() throws Exception {
		when(billingService.getAllPrescriptionsForPatientForBilling(21)).thenReturn(new ArrayList<Prescription>());
		
		ResultActions actions = mockMvc.perform(get("/billing/prescription/21"));
		actions.andExpect(status().isOk());
	}
	
	@Test
	@Order(4)
	@WithMockUser(authorities = "ROLE_ADMIN")
	void testGetConsultationsPendingBillingByPatientId() throws Exception {
		when(billingService.getConsultationsPendingBillingByPatientId(21)).thenReturn(new ArrayList<Consultation>());
		
		ResultActions actions = mockMvc.perform(get("/billing/consultation/21"));
		actions.andExpect(status().isOk());
	}
	
	@Test
	@Order(5)
	@WithMockUser(authorities = "ROLE_ADMIN")
	void testgetInPatientRecordByPatientId() throws Exception {
		when(billingService.getInPatientRecordByPatientId(21)).thenReturn(new ArrayList<InPatientRecord>());
		
		ResultActions actions = mockMvc.perform(get("/billing/inpatientrecord/21"));
		actions.andExpect(status().isOk());
	}
	
	@Test
	@Order(6)
	@WithMockUser(authorities = "ROLE_ADMIN")
	void testGetPendingBillingTestReportsByPatientId() throws Exception {
		when(billingService.getPendingBillingTestReportsByPatientId(21)).thenReturn(new ArrayList<TestReport>());
		
		ResultActions actions = mockMvc.perform(get("/billing/testreport/21"));
		actions.andExpect(status().isOk());
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
