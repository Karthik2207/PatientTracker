package com.tracker.patienttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tracker.patienttracker.model.Help;

@Repository
public interface HelpRepository extends JpaRepository<Help, Integer> {

	
}
