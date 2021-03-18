package com.tracker.patienttracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Constraint Validation Found")
public class ConstraintValidationException extends RuntimeException{

}
