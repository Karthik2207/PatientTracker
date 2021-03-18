package com.tracker.patienttracker.controller;

import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tracker.patienttracker.dto.PatientRecordDTO;
import com.tracker.patienttracker.model.Patient;
import com.tracker.patienttracker.model.PatientRecord;
import com.tracker.patienttracker.model.Prescription;
import com.tracker.patienttracker.model.TestReport;
import com.tracker.patienttracker.model.Treatment;
import com.tracker.patienttracker.model.User;
import com.tracker.patienttracker.security.JwtUtil;
import com.tracker.patienttracker.service.CustomUserDetailsService;
import com.tracker.patienttracker.service.PatientRecordService;

@WebMvcTest(PatientRecordController.class)
public class PatientRecordControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	PatientRecordService patientRecordService;
	
	@MockBean
	CustomUserDetailsService customUserDetailsService;
	
	@MockBean
	JwtUtil jwtUtil;
	
	@Test
	@WithMockUser(authorities = {"ROLE_DOCTOR"})
	public void addTreatmentTest() throws Exception {
		Treatment treatment = new Treatment();
		treatment.setTreatmentId(1);
		PatientRecordDTO patientRecordDTO = new PatientRecordDTO();
		patientRecordDTO.setTreatment(treatment);
		
		when(patientRecordService.addTreatment(patientRecordDTO)).thenReturn("Added Successfully");
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(patientRecordDTO);
		mockMvc.perform(MockMvcRequestBuilders
						.post("/patientrecord/addtreatment")
            .contentType(MediaType.APPLICATION_JSON)
						.content(json)
		        ).andExpect(MockMvcResultMatchers.status().isOk());
	}
  
	@Test
	@WithMockUser(authorities = {"ROLE_DOCTOR"})
	void getPrescriptionsTest() throws Exception {
		
		Prescription p1 = new Prescription();
		Prescription p2 = new Prescription();
		Set<Prescription> prescriptions = new HashSet<>();
		prescriptions.add(p2);
		prescriptions.add(p1);
		when(patientRecordService.prescriptions(1,2)).thenReturn(prescriptions);
		
		ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.get("/patientrecord/prescriptions/1/2"));
		
		actions.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	@WithMockUser(authorities = {"ROLE_DOCTOR"})
	void getTreatmentsTest() throws Exception {
		
		Treatment p1 = new Treatment();
		Treatment p2 = new Treatment();
		Set<Treatment> treatments = new HashSet<>();
		treatments.add(p2);
		treatments.add(p1);
		when(patientRecordService.treatments(1,2)).thenReturn(treatments);
		
		ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.get("/patientrecord/treatments/1/2"));
		
		actions.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	@WithMockUser(authorities = {"ROLE_DOCTOR"})
	void getTestReportsTest() throws Exception {
		
		TestReport p1 = new TestReport();
		TestReport p2 = new TestReport();
		Set<TestReport> testReports = new HashSet<>();
		testReports.add(p2);
		testReports.add(p1);
		when(patientRecordService.testReports(1,2)).thenReturn(testReports);
		
		ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.get("/patientrecord/testreports/1/2"));
		
		actions.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	@WithMockUser(authorities = {"ROLE_DOCTOR"})
	void addPrescriptionTest() throws Exception {
		PatientRecordDTO dto = new PatientRecordDTO();
		when(patientRecordService.addPrescription(dto)).thenReturn("Added Successfully");
		ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dto);
		mockMvc.perform(MockMvcRequestBuilders
						.post("/patientrecord/addprescription")
						.contentType(MediaType.APPLICATION_JSON)
						.content(json)
		).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	@WithMockUser(authorities = {"ROLE_DOCTOR"})
	public void updateTreatmentTest() throws Exception {
		Treatment treatment = new Treatment();
		treatment.setTreatmentId(1);
		PatientRecordDTO patientRecordDTO = new PatientRecordDTO();
		patientRecordDTO.setTreatment(treatment);
		
		when(patientRecordService.updateTreatment(patientRecordDTO)).thenReturn("Updated Successfully");
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(patientRecordDTO);
		mockMvc.perform(MockMvcRequestBuilders
						.post("/patientrecord/updatetreatment")
             .contentType(MediaType.APPLICATION_JSON)
						.content(json)
		).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	@WithMockUser(authorities = {"ROLE_DOCTOR"})
	public void addTestReportsTest() throws Exception {
		TestReport testReport = new TestReport();
		testReport.setTestResultId(1);
		PatientRecordDTO patientRecordDTO = new PatientRecordDTO();
		patientRecordDTO.setTestReport(testReport);
		
		when(patientRecordService.addTestReport(patientRecordDTO)).thenReturn("Added Successfully");
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(patientRecordDTO);
		mockMvc.perform(MockMvcRequestBuilders
						.post("/patientrecord/addtestreport")
            .contentType(MediaType.APPLICATION_JSON)
						.content(json)
		).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	@WithMockUser(authorities = {"ROLE_DOCTOR"})
	public void getAllPatientsForDoctorTest() throws Exception {
		Patient patient1 = new Patient(1,"B+ve",new User());
		Patient patient2 = new Patient(2,"O+ve",new User());
		Set<Patient> patients = new HashSet<Patient>();
		patients.add(patient1);
		patients.add(patient2);
		
		when(patientRecordService.getAllPatientsForDoctor(1)).thenReturn(patients);
		
		mockMvc.perform(MockMvcRequestBuilders
						.get("/patientrecord/patientids/1")
						).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	@WithMockUser(authorities = {"ROLE_DOCTOR"})
	public void getPatientRecordForPatientIdTest() throws Exception {
		PatientRecord patientRecord = new PatientRecord();
		patientRecord.setRecordId(1);
		Patient patient = new Patient(1,"B+ve",new User());
		patientRecord.setPatient(patient);
		
		when(patientRecordService.getPatientRecordForPatientId(1)).thenReturn(patientRecord);
		
		mockMvc.perform(MockMvcRequestBuilders
						.get("/patientrecord/patientrecord/1")
		).andExpect(MockMvcResultMatchers.status().isOk());
	}

	void updatePrescriptionTest() throws Exception {
		PatientRecordDTO dto = new PatientRecordDTO();
		when(patientRecordService.updatePrescription(dto)).thenReturn("Updateed Successfully");
		ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dto);
		mockMvc.perform(MockMvcRequestBuilders
						.post("/patientrecord/updateprescription")
						.contentType(MediaType.APPLICATION_JSON)
						.content(json)
		).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	@WithMockUser(authorities = {"ROLE_DOCTOR"})
	void getAllPatientsTest() throws Exception {
		Patient p1 = new Patient();
		Patient p2 = new Patient();
		Set<Patient> patients = new HashSet<>();
		patients.add(p1);
		patients.add(p2);
		when(patientRecordService.getAllPatientsForDoctor(2)).thenReturn(patients);
		ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.get("/patientrecord/patientids/2"));
		actions.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	@WithMockUser(authorities = {"ROLE_DOCTOR"})
	void getPatientById() throws Exception {
		PatientRecord p1 = new PatientRecord();
		when(patientRecordService.getPatientRecordForPatientId(1)).thenReturn(p1);
		ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.get("/patientrecord/patientrecord/1"));
		actions.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
}
