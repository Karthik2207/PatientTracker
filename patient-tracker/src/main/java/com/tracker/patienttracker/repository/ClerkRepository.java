package com.tracker.patienttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tracker.patienttracker.model.Clerk;

@Repository
public interface ClerkRepository extends JpaRepository<Clerk, Integer> {
 
}
