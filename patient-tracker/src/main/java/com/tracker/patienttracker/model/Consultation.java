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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@Entity
@ToString
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table(name = "consultation")
public class Consultation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int consultationId;
	
	@NotNull
	@OneToOne
	private Doctor doctorId;
	
	@NotNull
	@OneToOne
	private Patient patientId;
	
	@PastOrPresent
	@NotNull
	private Date date;
	
	@ManyToOne
	@JoinColumn(name="billingId")
	private Billing bill;
	
	private boolean paid;
}
