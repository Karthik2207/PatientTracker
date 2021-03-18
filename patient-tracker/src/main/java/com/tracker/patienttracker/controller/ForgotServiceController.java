package com.tracker.patienttracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tracker.patienttracker.dto.ForgotDTO;
import com.tracker.patienttracker.model.UserData;
import com.tracker.patienttracker.service.CustomUserDetailsService;

@RestController
@CrossOrigin
@RequestMapping("auth")
public class ForgotServiceController {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@PostMapping("/forgotpassword")
	public UserData ForgotPassword(@RequestBody ForgotDTO dto ) {
		return customUserDetailsService.serveForgotPassword(dto);
	}
	
	@PostMapping("/forgotuserid")
	public ResponseEntity<String> ForgotUserId(@RequestBody ForgotDTO dto ) {
		return customUserDetailsService.serveForgotUserId(dto);
	}
	
	@PostMapping("/resetpassword")
	public void resetPassword(@RequestBody UserData user) {
		customUserDetailsService.resetPassword(user);
	}
}
