package com.tracker.patienttracker.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tracker.patienttracker.model.Medicine;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Integer>{
	
	public Medicine findByMedicineName(String medicineName);
	
	public Set<Medicine> findByMedicineNameContaining(String medicineName);
}
