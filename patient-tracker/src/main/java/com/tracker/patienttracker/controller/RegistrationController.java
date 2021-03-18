package com.tracker.patienttracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tracker.patienttracker.dto.RegistrationData;
import com.tracker.patienttracker.exception.ConstraintValidationException;
import com.tracker.patienttracker.exception.RegistrationFailedException;
import com.tracker.patienttracker.model.Help;
import com.tracker.patienttracker.model.UserData;
import com.tracker.patienttracker.service.CustomUserDetailsService;
import com.tracker.patienttracker.service.HelpService;
import com.tracker.patienttracker.service.RegistrationService;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class RegistrationController {
	
	@Autowired
	private RegistrationService registrationService;
	@Autowired
	HelpService helpService; 
	@Autowired
	CustomUserDetailsService userDetailsService;
	
	@PostMapping("/registration")
	public String registration(@RequestBody RegistrationData registrationData) {
		String temp=registrationService.registration(registrationData);
		if(!temp.contains("Thanks For Registiring"))
			throw new RegistrationFailedException();
		return temp;
	}
	
	@PutMapping("/registration")
	public String registration2(@RequestBody RegistrationData registrationData) {
		String temp=registrationService.registration(registrationData);
		System.out.println(temp);
		if(!temp.contains("Thanks For Registiring"))
			throw new RegistrationFailedException();
		return temp;
	}
	
	@PostMapping("/login")
	public UserData loginCheck(@RequestBody UserData obj) {
		System.out.println("Login Method Calling by User Service CLass");
		return userDetailsService.login(obj);		
	}
	
	@PostMapping("/help")
	public String saveHelp(@RequestBody Help help) {
		if(helpService.saveIssues(help).contains("Issues"))
			return "Issue Submitted Successfully";
		else throw new ConstraintValidationException();
	}
}
