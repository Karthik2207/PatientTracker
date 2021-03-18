package com.tracker.patienttracker.exception;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGloabalExceptionHandler extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
        Map<String, Object> body = new LinkedHashMap<String, Object>();
        body.put("timestamp", new Date());
        body.put("status", status.value());
        //Get all errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);
        return new ResponseEntity<>(body, headers, status);
	}
	
	@ExceptionHandler(ConstraintViolationException.class) 
	public void  springHandleNotFound(HttpServletResponse response) throws IOException{
	  response.sendError(HttpStatus.BAD_REQUEST.value()); 
	}
 
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public void  springMethodArgHandleIssue(HttpServletResponse response) throws IOException{
		  response.sendError(HttpStatus.BAD_REQUEST.value(), "Arguments mismatched......"); 
	}
	
	@ExceptionHandler(PatientNotFoundException.class)
	public void patientNotFound(HttpServletResponse response) throws IOException{
		response.sendError(HttpStatus.NOT_FOUND.value(), "Patient Not Found"); 
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public void userNotFound(HttpServletResponse response) throws IOException{
		response.sendError(HttpStatus.NOT_FOUND.value(), "User Not Found"); 
	}
	
	@ExceptionHandler(DoctorNotFoundException.class)
	public void doctorNotFound(HttpServletResponse response) throws IOException{
		response.sendError(HttpStatus.NOT_FOUND.value(), "Doctor Not Found"); 
	}
	
	@ExceptionHandler(RegistrationFailedException.class)
	public void RegistraionFailed(HttpServletResponse response) throws IOException{
		response.sendError(HttpStatus.BAD_REQUEST.value(), "Registration Failed"); 
	}
	
	@ExceptionHandler(TreatmentNotFoundException.class)
	public void treatmentNotFound(HttpServletResponse response) throws IOException{
		response.sendError(HttpStatus.NOT_FOUND.value(), "Treatment Not Found"); 
	}

	@ExceptionHandler(MedicineNotFoundException.class)
	public void medicineNotFound(HttpServletResponse response) throws IOException{
		response.sendError(HttpStatus.NOT_FOUND.value(), "Medicine Not Found"); 
	}
	
	@ExceptionHandler(TestReportNotFoundException.class)
	public void testReportNotFound(HttpServletResponse response) throws IOException{
		response.sendError(HttpStatus.NOT_FOUND.value(), "Test Report Not Found"); 
	}

	@ExceptionHandler(Exception.class)
	public void handleAnyException(HttpServletResponse response, Exception ex) throws IOException{
		response.sendError(HttpStatus.BAD_REQUEST.value(), "Exception arised "+ex.getMessage()); 
	}

}
