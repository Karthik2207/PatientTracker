package com.tracker.patienttracker.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patientrecord")
@Component
public class PatientRecord {
		
	@Id
	private int recordId;
	
	@ManyToOne
	@JoinColumn(name = "doctorId")
	private Doctor doctor;
	

	@OneToMany(cascade = CascadeType.ALL)
	@OrderBy("treatmentId DESC")
	private Set<Treatment> treatments;
	
	@OneToMany(targetEntity = TestReport.class, cascade = CascadeType.ALL)
    @OrderBy("testResultId DESC")
	private Set<TestReport> testreports;
	
	@NotNull
	@OneToOne
	@JoinColumn(name = "patientId")
	private Patient patient;
	
	@OneToMany(targetEntity = Prescription.class, cascade = CascadeType.ALL)
    @OrderBy("prescriptionId DESC")
	private Set<Prescription> prescriptions;
	
	@PastOrPresent
	@NotNull
	private Date date;
}


