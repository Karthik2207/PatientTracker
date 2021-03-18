package com.tracker.patienttracker.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tracker.patienttracker.model.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Integer> {

	Set<Test> findByTestNameContaining(String name);
}
