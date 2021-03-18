package com.tracker.patienttracker.repository;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tracker.patienttracker.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query(value="select * from user  where approved=0",nativeQuery=true)
	public  Set<User> userApprovalPendingList();
	
	public Optional<User> findByUserIdAndRoleAndPassword(int id, String role, String password);

	public User findByContactNoAndDateOfBirth(String contactNo, Date dob);
	
}
