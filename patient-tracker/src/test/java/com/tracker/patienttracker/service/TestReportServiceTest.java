package com.tracker.patienttracker.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.tracker.patienttracker.model.Doctor;
import com.tracker.patienttracker.model.Patient;
import com.tracker.patienttracker.model.PatientRecord;
import com.tracker.patienttracker.model.TestReport;
import com.tracker.patienttracker.repository.TestReportRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class TestReportServiceTest {
	@Test
	@Order(1)
	void testGetPendingUpdateTestReports() {
		TestReportService testReportService = new TestReportService();
		
		TestReportRepository testReportRepository = Mockito.mock(TestReportRepository.class);
		
		when(testReportRepository.getPendingUpdateTestReports()).thenReturn(new ArrayList<TestReport>());
		
		testReportService.setTestReportRepository(testReportRepository);
		
		List<TestReport> testReportsPendingUpdate = testReportService.getPendingUpdateTestReports();
		
		assertNotNull(testReportsPendingUpdate);
	}
	
	@Test
	@Order(2)
	void testmodifyTestReport() {
		TestReportService testReportService = new TestReportService();
		
		TestReportRepository testReportRepository = Mockito.mock(TestReportRepository.class);
		
		TestReport testReportGenerated = new TestReport(1, new com.tracker.patienttracker.model.Test(), new Patient(), null, new Doctor(), true, false, false, new PatientRecord());
		
		when(testReportRepository.findById(21)).thenReturn(Optional.of(testReportGenerated));
		
		testReportGenerated.setTestResult("Sugar level normal");
		
		when(testReportRepository.save(testReportGenerated)).thenReturn(testReportGenerated);
		
		testReportService.setTestReportRepository(testReportRepository);
		
		TestReport testReport = testReportService.modifyTestReport(21, "Sugar level normal");
		
		assertThat(testReport.getTestResult()).isEqualTo("Sugar level normal");
	}
}
