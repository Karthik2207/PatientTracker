package com.tracker.patienttracker.model;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","patientRecord"})
@Table(name="testreport")
public class TestReport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int testResultId;
	@OneToOne
	@NotNull
	private Test test;
	
	@NotNull(message="Please provide Patient Id")
	@OneToOne
	private Patient patient;
	
	private String testResult;
	
	@NotNull
	@OneToOne
	private Doctor doctor;
	
	private boolean requested;

	private boolean paid;

	private boolean billed;
	
	@ManyToOne()
	@JoinColumn(name="recordId")
	private PatientRecord patientRecord;
}
