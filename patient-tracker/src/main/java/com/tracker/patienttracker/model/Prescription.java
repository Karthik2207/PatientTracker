package com.tracker.patienttracker.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Repository
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","patientRecord"})
@Table(name="prescription")
public class Prescription {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull(message = "Prescription Id is required")
	private int prescriptionId;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="prescription_medicines", joinColumns = @JoinColumn(name ="prescriptionId"),inverseJoinColumns = @JoinColumn(name="medicineQuantityId") )
	private Set<MedicineQuantity> medicineQuantities;
	
	@NotNull(message = "Prescription Cost is required")
	private double prescriptionCost;
	
	@NotNull(message = "Paid is required")
	private boolean paid;
	
	@NotNull(message = "Billed is required")
	private boolean billed;
	
	@ManyToOne
	@JoinColumn(name="recordId")
	private PatientRecord patientRecord;
	
	@Override
	public boolean equals(Object obj) {
		Prescription prescription = (Prescription)obj;
		if(prescriptionId == prescription.getPrescriptionId()) {
			return true;
		}
		return false;
	}
	
}
