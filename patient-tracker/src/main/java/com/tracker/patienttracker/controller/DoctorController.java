package com.tracker.patienttracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tracker.patienttracker.model.Doctor;
import com.tracker.patienttracker.service.DoctorService;

@RestController
@CrossOrigin
@RequestMapping("/doctors")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;
	
	@GetMapping
	public ResponseEntity<List<Doctor>> getAllDoctors() {
		
		return ResponseEntity.ok(doctorService.getAllDoctors());
	}
	
}
