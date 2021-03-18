package com.tracker.patienttracker.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.tracker.patienttracker.model.TestReport;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
class TestReportRepositoryTest {
	@Autowired
	TestReportRepository testReportRepository;
	
	@Test
	@Order(1)
	void testGetPendingUpdateTestReports() {
		List<TestReport> pendingUpdateTestReportsList = testReportRepository.getPendingUpdateTestReports();
		
		assertNotNull(pendingUpdateTestReportsList);
	}
}
