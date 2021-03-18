package com.tracker.patienttracker.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.tracker.patienttracker.model.InPatientRecord;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
class InPatientRecordRepositoryTest {
	@Autowired
	InPatientRecordRepository inPatientRecordRepository;
	
	@Test
	@Order(1)
	void testGetAllInPatientRecords() {
		List<InPatientRecord> inPatientRecords = inPatientRecordRepository.getAllInPatientRecords();
		
		assertNotNull(inPatientRecords);
	}
	
	@Test
	@Order(2)
	void testGetInPatientRecordsByPatientId() {
		List<InPatientRecord> inPatientRecords = inPatientRecordRepository.getInPatientRecordsByPatientId(22);
		
		assertNotNull(inPatientRecords);
	}
	
	@Test
	@Order(2)
	void testGetInPatientRecordByPatientId() {
		InPatientRecord inPatientRecord = inPatientRecordRepository.getInPatientRecordByInPatientRecordId(21);
		
		assertNotNull(inPatientRecord);
		assertEquals(22, inPatientRecord.getPatient().getPatientId());
	}
}
