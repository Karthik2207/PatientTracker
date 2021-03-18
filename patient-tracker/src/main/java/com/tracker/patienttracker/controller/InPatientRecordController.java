package com.tracker.patienttracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tracker.patienttracker.model.InPatientRecord;
import com.tracker.patienttracker.service.InPatientRecordService;

@RestController
@RequestMapping("/inpatientrecord")
@CrossOrigin
public class InPatientRecordController {
	@Autowired
	InPatientRecordService inPatientRecordService;
	
	@GetMapping
	public ResponseEntity<List<InPatientRecord>> getAllInPatientRecords() {
		return ResponseEntity.ok(inPatientRecordService.getAllInPatientRecords());
	}
	
	@GetMapping("/{inPatientRecordId}")
	public ResponseEntity<InPatientRecord> getInPatientRecordByInPatientRecordId(@PathVariable("inPatientRecordId") int inPatientRecordId) {
		return ResponseEntity.ok(inPatientRecordService.getInPatientRecordByInPatientRecordId(inPatientRecordId));
	}
	
	@PostMapping("/{patientId}/{roomNo}")
	public ResponseEntity<InPatientRecord> addInPatientRecord(@PathVariable("patientId") int patientId, @PathVariable("roomNo") int roomNo, @RequestBody InPatientRecord inPatientRecord) {
		return ResponseEntity.ok(inPatientRecordService.addInPatientRecord(patientId, roomNo, inPatientRecord));
	}
	
	@PutMapping
	public ResponseEntity<InPatientRecord> updateInPatientRecord(@RequestBody InPatientRecord inPatientRecord) {
		return ResponseEntity.ok(inPatientRecordService.updateInPatientRecord(inPatientRecord));
	}
}
