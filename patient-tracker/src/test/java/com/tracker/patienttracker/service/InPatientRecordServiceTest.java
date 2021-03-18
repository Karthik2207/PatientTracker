package com.tracker.patienttracker.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.tracker.patienttracker.model.InPatientRecord;
import com.tracker.patienttracker.model.Patient;
import com.tracker.patienttracker.model.Room;
import com.tracker.patienttracker.repository.InPatientRecordRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class InPatientRecordServiceTest {
	@MockBean
	InPatientRecordRepository inPatientRecordRepository;
	
	@MockBean
	PatientService patientService;
	
	@MockBean
	RoomService roomService;
	
	@Test
	@Order(1)
	void testGetAllInPatientRecords() {
		InPatientRecordService inPatientRecordService = new InPatientRecordService();
		
		when(inPatientRecordRepository.getAllInPatientRecords()).thenReturn(new ArrayList<InPatientRecord>());
		
		inPatientRecordService.setInPatientRecordRepository(inPatientRecordRepository);
		
		assertNotNull(inPatientRecordService.getAllInPatientRecords());
	}
	
	@Test
	@Order(2)
	void testGetInPatientRecordByInPatientRecordId() {
		InPatientRecordService inPatientRecordService = new InPatientRecordService();
		
		InPatientRecord inPatientRecord = new InPatientRecord();
		
		when(inPatientRecordRepository.getInPatientRecordByInPatientRecordId(21)).thenReturn(inPatientRecord);
		
		inPatientRecordService.setInPatientRecordRepository(inPatientRecordRepository);
		
		assertEquals(inPatientRecord, inPatientRecordService.getInPatientRecordByInPatientRecordId(21));
	}
	
	@Test
	@Order(3)
	void testAddInPatientRecord() {
		InPatientRecordService inPatientRecordService = new InPatientRecordService();
		
		InPatientRecord inPatientRecord = new InPatientRecord();
		
		Patient patient = new Patient();
		Room room = new Room();
		
		when(patientService.getPatient(22)).thenReturn(patient);
		when(roomService.getRoomByNo(21)).thenReturn(room);
		
		when(inPatientRecordRepository.save(inPatientRecord)).thenReturn(inPatientRecord);
		
		inPatientRecordService.setInPatientRecordRepository(inPatientRecordRepository);
		inPatientRecordService.setPatientService(patientService);
		inPatientRecordService.setRoomService(roomService);
		
		assertEquals(inPatientRecord, inPatientRecordService.addInPatientRecord(22, 21, inPatientRecord));
	}
	
	@Test
	@Order(4)
	void testUpdateInPatientRecord() {
		InPatientRecordService inPatientRecordService = new InPatientRecordService();
		
		InPatientRecord inPatientRecord = new InPatientRecord();
		
		when(inPatientRecordRepository.getInPatientRecordByInPatientRecordId(inPatientRecord.getInPatientRecordId())).thenReturn(inPatientRecord);
		when(inPatientRecordRepository.save(inPatientRecord)).thenReturn(inPatientRecord);
		
		inPatientRecordService.setInPatientRecordRepository(inPatientRecordRepository);
		
		assertEquals(inPatientRecord, inPatientRecordService.updateInPatientRecord(inPatientRecord));
	}
}
