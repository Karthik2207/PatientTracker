package com.tracker.patienttracker.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tracker.patienttracker.model.Help;
import com.tracker.patienttracker.repository.HelpRepository;

@Service
public class HelpService {
	
	@Autowired
	HelpRepository helpRepository; 
	
	@Transactional
	public String saveIssues(Help help) {
		helpRepository.save(help);
		return "Issues have been saved successfully";
		
	}
}
