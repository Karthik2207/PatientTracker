package com.tracker.patienttracker.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Repository;

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
@Table(name = "medicine")
public class Medicine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull(message = "Medicine Id is required")
	@Min(value = 1, message = "Medicine Id has to be greater than or equal to 1")
	private int medicineId;
	@NotNull(message = "Medicine Name is required")
	private String medicineName;
	@NotNull(message = "Medicine Cost is required")
	@Digits(integer = 32, fraction = 2, message = "Please Enter a valid Cost")
	private double medicineCost;
	
	@Override
	public String toString() {
		return "Medicine(medicineId=" + medicineId + ", medicineName=" + medicineName + ", medicineCost="
				+ String.format("%.2f",medicineCost) + ")";
	}
}