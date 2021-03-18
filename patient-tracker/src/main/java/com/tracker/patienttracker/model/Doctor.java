package com.tracker.patienttracker.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "doctor")
@Component
public class Doctor {
	@Id
	@NotNull(message = "Doctor Id is required")
	@Min(value = 1, message = "Doctor Id has to be greater than or equal to 1")
	private int doctorId;
	
	@NotEmpty(message = "Qualification cannot be empty")
	@NotBlank(message = "Qualification cannot be blank or whitespace")
	private String qualification;
	@NotEmpty(message = "Specialization cannot be empty")
	@NotBlank(message = "Specialization cannot be blank or whitespace")
	private String specialization;
	@NotNull(message = "Consultation Fee is required")
	@Digits(integer = 32, fraction = 2, message = "Please Enter a valid fee")
	private double consultationFee;

	@NotNull(message = "User Id is required")
	@OneToOne
	@JoinColumn(name = "userId")
	private User user;
}
