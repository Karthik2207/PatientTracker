package com.tracker.patienttracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tracker.patienttracker.model.TestReport;

@Repository
public interface TestReportRepository extends JpaRepository<TestReport, Integer> {
	// Update Test Results Home Page
	//Select * From test_report where testResult = null;
	@Query(value = "Select t From TestReport t where t.testResult = null")
	public List<TestReport> getPendingUpdateTestReports(); // Test pending
	
	// Test Results Yet to be billed but result is not null
	//Select t From test_report t where t.testResult = null and t.billed;
	@Query(value = "Select t From TestReport t left join t.patient p where t.testResult <> null and p.patientId = :patientId and t.billed = false")
	public List<TestReport> getPendingBillingTestReportsByPatientId(int patientId); // Test pending
}
