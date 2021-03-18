package com.tracker.patienttracker.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Repository
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "medicinequantity")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","prescriptions"})
public class MedicineQuantity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull(message = "Medicine Quantity Id is required")
	private int medicineQuantityid;
	
	@ManyToMany(mappedBy = "medicineQuantities")
	private Set<Prescription> prescriptions;
	
	@NotNull
	@OneToOne
	private Medicine medicine;
	
	@Min(value = 1)
	private int noOfDays;
	
	@NotNull(message = "Quantity is required")
	@Min(value=1)
	private int quantity;
}