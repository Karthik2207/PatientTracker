package com.tracker.patienttracker.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.tracker.patienttracker.model.Patient;
import com.tracker.patienttracker.model.User;
import com.tracker.patienttracker.util.DateUtil;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace =Replace.NONE)
public class PatientRepositoryTest {
	
	@Autowired
	PatientRepository patientRepository; 
	@Autowired
	UserRepository userRepository;
	
	public User userMethod() {
		User obj= new User();
		obj.setFirstName("abcdefg");
		obj.setLastName("abcdefg");
		obj.setGender("male");
		obj.setDateOfBirth(DateUtil.convertToDate("22/07/1998"));
		obj.setContactNo("1234567890");
		obj.setPassword("K@1234kask");
		obj.setAddress("akasakakas");
		obj.setRole("ROLE_PATIENT");
		return obj;
	}
	@Test
    @Rollback(true)
	public void testaddPatientRecord() {
		PatientRepositoryTest obj=new PatientRepositoryTest();
		User userObj=userRepository.save(obj.userMethod());
		Patient obj1=new Patient(userObj.getUserId(),"O+ve",userObj);
		Patient obj2=patientRepository.save(obj1);
		assertEquals(obj2, obj1);		
	}
	
	@Test
    @Rollback(true)
	public void testfetchPatientRecord() {
		PatientRepositoryTest obj=new PatientRepositoryTest();
		User userObj=userRepository.save(obj.userMethod());
		Patient obj1=new Patient(userObj.getUserId(),"O+ve",userObj);
		Patient obj2=patientRepository.save(obj1);
		assertEquals(patientRepository.findById(userObj.getUserId()).get(),obj2);
	}
	@Test
	@Rollback(true)
	public void testPatientList()
	{
		PatientRepository patientRepository=Mockito.mock(PatientRepository.class);
		PatientRepositoryTest obj=new PatientRepositoryTest();
		User userObj=userRepository.save(obj.userMethod());
		Patient obj1=new Patient(userObj.getUserId(),"O+ve",userObj);
		Patient obj2=patientRepository.save(obj1);
		assertEquals(patientRepository.findAll().size(),0);
	}
}
