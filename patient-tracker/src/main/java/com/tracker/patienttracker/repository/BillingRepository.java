package com.tracker.patienttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tracker.patienttracker.model.Billing;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Integer> {
	//Load Bill by patientId
	//Select * from Billing where patientId = patientId
	@Query(value = "Select b From Billing b left join b.patient p where p.patientId=:patientId")
	public Billing getBillingByPatientId(int patientId);
	
	
}
