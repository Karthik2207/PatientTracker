package com.tracker.patienttracker.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tracker.patienttracker.model.Test;
import com.tracker.patienttracker.model.TestReport;
import com.tracker.patienttracker.repository.PatientRecordRepository;
import com.tracker.patienttracker.repository.TestRepository;

@Service
public class TestService {

	@Autowired
	TestRepository testRepository;
	@Autowired
	PatientRecordRepository  patientRecordRepository ;
	
	public Test getTestDetails(int testId)
	{
		return testRepository.findById(testId).get();
	}
	
	public Set<Test> getTestList(int patientId)
	{	 Set<Test> test=new HashSet<Test>();
		Set<TestReport> t=patientRecordRepository.findById(patientId).get().getTestreports();
		for(TestReport a:t)
		{
			test.add(a.getTest());
		}
		return test;
	}
	
	public Set<Test> getTestListContainingName(String testName) {
		return testRepository.findByTestNameContaining(testName);
	}
	
}
