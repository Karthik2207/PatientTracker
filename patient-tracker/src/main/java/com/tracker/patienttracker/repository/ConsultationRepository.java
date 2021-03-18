package com.tracker.patienttracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tracker.patienttracker.model.Consultation;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Integer>{
	//List of treatments By Patient Id that aren't billed
	//Select c from Consultation c left join c.patient p where p.patientId = :patientId and c.bill = null;
	@Query(value = "Select c from Consultation c left join c.patientId p where p.patientId = :patientId and c.bill = null")
	public List<Consultation> getConsultationsPendingBillingByPatientId(int patientId);
}
