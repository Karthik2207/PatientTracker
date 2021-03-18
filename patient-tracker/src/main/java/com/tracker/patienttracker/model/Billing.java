package com.tracker.patienttracker.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

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
@Table(name="billing")
public class Billing {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int billingId;

	@NotNull(message="Please provide Patient Id")
	@ManyToOne
	@JoinColumn(name="patientId")
	private Patient patient;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="inPatientRecordId")
	private InPatientRecord inPatientRecord;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="prescriptionId")
	private Prescription prescription;

	@Min(value = 0, message = "The value must not be negative")
	private double amount;

	@PastOrPresent
	@NotNull(message="Please provide TimeStamp")
	private LocalDateTime timestamp;

	@FutureOrPresent
	@NotNull(message="Please provide Due Date")
	private LocalDateTime dueDate;

	private boolean paid;

	@NotNull(message="UserId can not be null")
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;          //Creator of the bill

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bill")
	private Set<Consultation> consultations;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<TestReport> testReports;
}