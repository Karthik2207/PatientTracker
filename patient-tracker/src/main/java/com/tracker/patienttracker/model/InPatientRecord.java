package com.tracker.patienttracker.model;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "inpatientrecord")
public class InPatientRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int inPatientRecordId;
	
	@NotNull(message = "Patient cannot be null")
	@ManyToOne
	@JoinColumn(name = "patientId")
	private Patient patient;
	
	@PastOrPresent
	@NotNull(message = "Admission Date cannot be null")
	@Temporal(TemporalType.DATE)
	private Date admissionDate;
	@PastOrPresent
	@Temporal(TemporalType.DATE)
	private Date dischargeDate;
	
	@NotNull(message = "Room cannot be null")
	@OneToOne
	@JoinColumn(name = "roomNo")
	private Room room;
	
	private boolean nursingChargesBilled;
	private boolean nursingChargesPaid;
	private boolean roomChargesPaid;
	private boolean	roomChargesBilled;
}
