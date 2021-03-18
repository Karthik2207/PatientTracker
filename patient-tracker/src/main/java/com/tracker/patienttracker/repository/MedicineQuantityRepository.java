package com.tracker.patienttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tracker.patienttracker.model.MedicineQuantity;

@Repository
public interface MedicineQuantityRepository extends JpaRepository<MedicineQuantity, Integer> {

}
