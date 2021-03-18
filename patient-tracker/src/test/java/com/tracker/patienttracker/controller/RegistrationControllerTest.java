package com.tracker.patienttracker.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tracker.patienttracker.dto.RegistrationData;
import com.tracker.patienttracker.model.Help;
import com.tracker.patienttracker.model.UserData;
import com.tracker.patienttracker.security.JwtUtil;
import com.tracker.patienttracker.service.CustomUserDetailsService;
import com.tracker.patienttracker.service.HelpService;
import com.tracker.patienttracker.service.RegistrationService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RegistrationController.class)
class RegistrationControllerTest {
		
	@MockBean
	JwtUtil jwtUtil;
	
	@Autowired
	private MockMvc mvc;
	@MockBean
	private RegistrationService registrationService;
	@MockBean
	HelpService helpService;
	@MockBean
	CustomUserDetailsService userDetailsService;
	
	@Test
	void registrationTestPass() throws Exception {
		RegistrationData registrationData=new RegistrationData("abc", "abc", "male", "1998-07-22", "1234567890", 
				"aaaas@123", "123sas", "ROLE_DOCTOR", "MS", "HEART", 123.4, "O+ve", 1, 1 , 2);
		ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(registrationData);
		when(registrationService.registration(registrationData)).thenReturn("Thanks For Registiring Please wait for the Approval Your UserId is 1");		
        MvcResult mvcResult=mvc.perform(MockMvcRequestBuilders.post("/users/registration")
                .contentType(MediaType.APPLICATION_JSON).content(json)).andReturn();
        String content=mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Thanks For Registiring Please wait for the Approval Your UserId is 1");
	}
	
	@Test
	void registrationTestFailed() throws Exception {
		RegistrationData registrationData=new RegistrationData("abc", "abc", "male", "1998-07-22", "1234567890", 
				"aaaas", "123sas", "ROLE_DOCTOR", "MS", "HEART", 123.4, "O+ve", 1, 1 , 2);
		ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(registrationData);
		when(registrationService.registration(registrationData)).thenReturn("");		
        MvcResult mvcResult=mvc.perform(MockMvcRequestBuilders.post("/users/registration")
                .contentType(MediaType.APPLICATION_JSON).content(json)).andReturn();
        int result=mvcResult.getResponse().getStatus();        
		assertEquals(400,result);
	}
	
	@Test
	void registration2TestPass() throws Exception {
		RegistrationData registrationData=new RegistrationData("abc", "abc", "male", "1998-07-22", "1234567890", 
				"aaaas@123", "123sas", "ROLE_DOCTOR", "MS", "HEART", 123.4, "O+ve", 1, 1 , 2);
		ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(registrationData);
		when(registrationService.registration(registrationData)).thenReturn("Thanks For Registiring Please wait for the Approval Your UserId is 1");		
        MvcResult mvcResult=mvc.perform(MockMvcRequestBuilders.put("/users/registration")
                .contentType(MediaType.APPLICATION_JSON).content(json)).andReturn();
        String content=mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Thanks For Registiring Please wait for the Approval Your UserId is 1");
	}
	
	@Test
	void registration2TestFailed() throws Exception {
		RegistrationData registrationData=new RegistrationData("abc", "abc", "male", "1998-07-22", "1234567890", 
				"aaaas", "123sas", "ROLE_DOCTOR", "MS", "HEART", 123.4, "O+ve", 1, 1 , 2);
		ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(registrationData);
		when(registrationService.registration(registrationData)).thenReturn("");		
        MvcResult mvcResult=mvc.perform(MockMvcRequestBuilders.put("/users/registration")
                .contentType(MediaType.APPLICATION_JSON).content(json)).andReturn();
        int result=mvcResult.getResponse().getStatus();        
		assertEquals(400,result);
	}
	
	@Test
	void loginTest() throws Exception {
		UserData obj=new UserData(1, "kartik123@", "asssbsdsjksdkmsd", "ROLE_DOCTOR");
		ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		when(userDetailsService.login(obj)).thenReturn(obj);
		MvcResult mvcResult=mvc.perform(MockMvcRequestBuilders.post("/users/login")
                .contentType(MediaType.APPLICATION_JSON).content(json)).andReturn();
		int result=mvcResult.getResponse().getStatus();
		assertEquals(200,result);
	}
	
	@Test
	@WithMockUser(authorities = {"ROLE_DOCTOR"})
	void helpTest() throws Exception {
		Help obj=new Help(1, "Issue", "Description");
		ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		when(helpService.saveIssues(obj)).thenReturn("Issues have been saved successfully");
		MvcResult mvcResult=mvc.perform(MockMvcRequestBuilders.post("/users/help")
                .contentType(MediaType.APPLICATION_JSON).content(json)).andReturn();
		String obj1=mvcResult.getResponse().getContentAsString();
		assertEquals(obj1, "Issue Submitted Successfully");		
	}
	
	@Test
	@WithMockUser(authorities = {"ROLE_DOCTOR"})
	void helpTestFail() throws Exception {
		Help obj=new Help(1, "Issue", "Description");
		ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		when(helpService.saveIssues(obj)).thenReturn("");
		MvcResult mvcResult=mvc.perform(MockMvcRequestBuilders.post("/users/help")
                .contentType(MediaType.APPLICATION_JSON).content(json)).andReturn();
		int result=mvcResult.getResponse().getStatus();        
		assertEquals(400,result);
	}
}