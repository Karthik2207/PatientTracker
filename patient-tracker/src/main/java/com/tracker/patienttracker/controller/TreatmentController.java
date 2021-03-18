package com.tracker.patienttracker.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tracker.patienttracker.model.Treatment;
import com.tracker.patienttracker.service.TreatmentService;
@CrossOrigin
@RestController
@RequestMapping("/treatment")
public class TreatmentController {
	@Autowired
	TreatmentService treatmentService;
	
	@GetMapping("/history/{patientId}")
	public  Set<Treatment> getTreatmentHistory(@PathVariable int patientId)
	{
		return treatmentService.getTreatmentHistory(patientId);
	}
	
	@PutMapping("/update")
	public Treatment updateTreatment(@RequestBody Treatment treatment) {
		return treatmentService.updateTreatment(treatment);
	}

}
