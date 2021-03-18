package com.tracker.patienttracker.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.tracker.patienttracker.dto.ForgotDTO;
import com.tracker.patienttracker.model.AuthResponse;
import com.tracker.patienttracker.model.UserData;


public interface CustomUserDetailsService extends UserDetailsService {
	
	public UserData login(@RequestBody UserData userlogincredentials);

	
	public AuthResponse getValidity(@RequestHeader("Authorization") final String token);

	public UserData serveForgotPassword(ForgotDTO dto);
	
	public ResponseEntity<String>  serveForgotUserId(ForgotDTO dto);
	
	public void resetPassword(UserData user);
	

}
