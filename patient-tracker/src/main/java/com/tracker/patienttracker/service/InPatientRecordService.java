package com.tracker.patienttracker.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tracker.patienttracker.model.InPatientRecord;
import com.tracker.patienttracker.model.Patient;
import com.tracker.patienttracker.model.Room;
import com.tracker.patienttracker.repository.InPatientRecordRepository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Service
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InPatientRecordService {
	@Autowired
	InPatientRecordRepository inPatientRecordRepository;
	
	@Autowired
	PatientService patientService;
	
	@Autowired
	RoomService roomService;
	
	public List<InPatientRecord> getAllInPatientRecords() {
		return inPatientRecordRepository.getAllInPatientRecords();
	}
	
	public InPatientRecord getInPatientRecordByInPatientRecordId(int inPatientRecordId) {
		return inPatientRecordRepository.getInPatientRecordByInPatientRecordId(inPatientRecordId);
	}
	
	public InPatientRecord addInPatientRecord(int patientId, int roomNo, InPatientRecord inPatientRecord) {
		Patient patient = patientService.getPatient(patientId);
		Room room = roomService.getRoomByNo(roomNo);
		
		inPatientRecord.setPatient(patient);
		inPatientRecord.setRoom(room);
		
		inPatientRecord = inPatientRecordRepository.save(inPatientRecord);
				
		return inPatientRecord;
	}
	
	@Transactional
	public InPatientRecord updateInPatientRecord(InPatientRecord inPatientRecord) {
		InPatientRecord inPatientRecordDB = inPatientRecordRepository.getInPatientRecordByInPatientRecordId(inPatientRecord.getInPatientRecordId());
		
		inPatientRecordDB.setDischargeDate(inPatientRecord.getDischargeDate());
		
		if(inPatientRecord.getAdmissionDate() != null) {
			inPatientRecordDB.setAdmissionDate(inPatientRecord.getAdmissionDate());
		}
		
		return inPatientRecordRepository.save(inPatientRecordDB);
	}
	
	public List<InPatientRecord> getInPatientRecordByPatientId(int patientId) {
		return inPatientRecordRepository.getInPatientRecordsByPatientId(patientId);
	}
	
	@Transactional
	public InPatientRecord updateInPatientRecordBilling(InPatientRecord inPatientRecord) {
		return inPatientRecordRepository.save(inPatientRecord);
	}
}