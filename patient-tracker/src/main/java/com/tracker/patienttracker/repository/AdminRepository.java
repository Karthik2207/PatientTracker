package com.tracker.patienttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tracker.patienttracker.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

	
}
