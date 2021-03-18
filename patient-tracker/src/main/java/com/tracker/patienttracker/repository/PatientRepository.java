package com.tracker.patienttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tracker.patienttracker.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
