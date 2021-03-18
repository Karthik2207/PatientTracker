package com.tracker.patienttracker.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tracker.patienttracker.exception.TestReportNotFoundException;
import com.tracker.patienttracker.model.TestReport;
import com.tracker.patienttracker.repository.TestReportRepository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Service
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestReportService {
	@Autowired
	TestReportRepository testReportRepository;
	
	public List<TestReport> getPendingUpdateTestReports() {
		return testReportRepository.getPendingUpdateTestReports();
	}
	
	public TestReport modifyTestReport(int testReportId, String testResult) {
		TestReport testReport=testReportRepository.findById(testReportId).get();
		
		testReport.setTestResult(testResult);
		
		return testReportRepository.save(testReport);
	}
	
	public List<TestReport> getPendingBillingTestReportsByPatientId(int patientId) {
		return testReportRepository.getPendingBillingTestReportsByPatientId(patientId);
	}
	
	public TestReport updateRequestedInTestReport(int testResultId) {
		Optional<TestReport> optionalTestReport = testReportRepository.findById(testResultId);
		if(!optionalTestReport.isPresent())
			throw new TestReportNotFoundException();
		TestReport testReport = optionalTestReport.get();
		testReport.setRequested(true);
		return testReportRepository.save(testReport);
	}
	
	public TestReport updateTestReport(TestReport testReport) {
		return testReportRepository.save(testReport);
	}
}
