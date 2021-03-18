package com.tracker.patienttracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tracker.patienttracker.dto.TestResultDTO;
import com.tracker.patienttracker.model.TestReport;
import com.tracker.patienttracker.service.TestReportService;

@RestController
@RequestMapping("/testreport")
@CrossOrigin
public class TestReportController {
	@Autowired
	TestReportService testReportService;
	
	@GetMapping
	public ResponseEntity<List<TestReport>> getPendingUpdateTestReports() {
		return ResponseEntity.ok(testReportService.getPendingUpdateTestReports());
	}
	
	@PutMapping("/{testResultId}")
	public ResponseEntity<String> updateTestResult(@PathVariable("testResultId") int testResultId, @RequestBody TestResultDTO testResultDTO) {
		TestReport testReport =  testReportService.modifyTestReport(testResultId, testResultDTO.getTestResult());
		
		String responseString = null;
		
		if(testReport.getTestResult().equals(testResultDTO.getTestResult()))
			responseString = "Updated";
		else
			responseString = "Failed";
		
		return ResponseEntity.ok(responseString);
	}
	
	@PutMapping("/requested/{testResultId}")
	public ResponseEntity<TestReport> updateRequestedInTestReport(@PathVariable("testResultId") int testResultId){
		return ResponseEntity.ok(testReportService.updateRequestedInTestReport(testResultId));
	}
}
