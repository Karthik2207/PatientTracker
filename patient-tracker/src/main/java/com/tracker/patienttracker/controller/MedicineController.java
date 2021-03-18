package com.tracker.patienttracker.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tracker.patienttracker.model.Medicine;
import com.tracker.patienttracker.service.MedicineService;

@RestController
@CrossOrigin
@RequestMapping("/medicine")
public class MedicineController {

	@Autowired
	private MedicineService medicineService;
	
	@GetMapping("/{name}")
	public ResponseEntity<Set<Medicine>> getMedicinesContainingName(@PathVariable("name") String medicineName) {
		System.out.println(medicineName);
		Set<Medicine> medicines = medicineService.getMedicinesContainingName(medicineName);
		return ResponseEntity.ok(medicines);
	}
}
