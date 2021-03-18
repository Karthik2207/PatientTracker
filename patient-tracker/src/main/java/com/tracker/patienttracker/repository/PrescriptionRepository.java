package com.tracker.patienttracker.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tracker.patienttracker.model.PatientRecord;
import com.tracker.patienttracker.model.Prescription;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Integer> {
	public Set<Prescription> findByPatientRecordAndBilledFalse(PatientRecord patientRecord);
}
