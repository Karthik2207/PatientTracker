package com.tracker.patienttracker.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "treatment")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","patientRecord"})
public class Treatment {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int treatmentId;
	
	@NotBlank(message = "Treatment Description is mandatory")
	private String treatmentDescription;
	
	@NotNull(message = "Treatment Cost is mandatory")
	@DecimalMin(value="0.0")
	private double treatmentCost;
	
	private String dietExcerciseDescription;

	@ManyToOne
	@JoinColumn(name="recordId")
	private PatientRecord patientRecord;
}
