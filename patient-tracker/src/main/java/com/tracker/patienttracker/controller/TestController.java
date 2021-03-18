package com.tracker.patienttracker.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tracker.patienttracker.model.Test;
import com.tracker.patienttracker.model.TestReport;
import com.tracker.patienttracker.service.TestService;

@RestController
@CrossOrigin
@RequestMapping("/test")
public class TestController {

	@Autowired
	private TestService testService;
	
	@GetMapping("/details/{patientId}")
	public Set<Test> getTestList(@PathVariable int patientId)
	{
		return testService.getTestList(patientId);
  }
	
	
	@GetMapping("/{name}")
	public ResponseEntity<Set<Test>> getTestByName(@PathVariable("name") String name) {
		Set<Test> tests = testService.getTestListContainingName(name);
		return ResponseEntity.ok(tests);
	}
}
