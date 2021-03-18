package com.tracker.patienttracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tracker.patienttracker.dto.RegistrationData;
import com.tracker.patienttracker.model.Admin;
import com.tracker.patienttracker.model.Clerk;
import com.tracker.patienttracker.model.Consultation;
import com.tracker.patienttracker.model.Doctor;
import com.tracker.patienttracker.model.Patient;
import com.tracker.patienttracker.model.PatientRecord;
import com.tracker.patienttracker.model.User;
import com.tracker.patienttracker.repository.AdminRepository;
import com.tracker.patienttracker.repository.ClerkRepository;
import com.tracker.patienttracker.repository.DoctorRepository;
import com.tracker.patienttracker.repository.PatientRepository;
import com.tracker.patienttracker.repository.UserRepository;
import com.tracker.patienttracker.util.DateUtil;
import com.tracker.patienttracker.validator.ConstraintValidation;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegistrationServiceTest {

	@InjectMocks
	RegistrationService registrationService;
	
	
	@Mock
	RegistrationData registrationData; 
	@Mock
	UserRepository userRepository;
	@Mock
	DoctorRepository doctorRepository;
	@Mock
	AdminRepository adminRepository;
	@Mock
	PatientRepository patientRepository;
	@Mock
	ClerkRepository clerkRepository;
	@Mock
	ConstraintValidation constraintValidation; 
	@Mock
	DoctorService docService;
	@Mock
	PatientRecordService prService;
	@Mock
	PatientRecord patientRecord;
	@Mock
	Consultation consult;
	@Mock
	ConsultationService consultationService;
	
	@Test
	public void registrationTestForDoctor() {
		RegistrationData registrationData=new RegistrationData("abc", "abc", "male", "1998-07-22", "1234567890", 
				"aaaas@123", "123sas", "ROLE_DOCTOR", "MS", "HEART", 123.4, "O+ve", 1, 1, 1);
		Date dateOfBirth=new DateUtil().convertToDate1("1998-07-22");
		User obj=new User(1, "abc", "abc", "male", dateOfBirth, "1234567890", 
				"aaaas@123", "123sas", "ROLE_DOCTOR", 1);
		registrationService.setObj(obj);
		when(constraintValidation.validationCheck(obj)).thenReturn("");		
		when(userRepository.save(obj)).thenReturn(obj);		
		Doctor obj1=new Doctor(1,"MS", "HEART",123.4,obj);
		registrationService.setObj1(obj1);
		when(constraintValidation.validationCheck(obj1)).thenReturn("");
		when(doctorRepository.save(obj1)).thenReturn(obj1);
		assertEquals("Thanks For Registiring Please wait for the Approval Your UserId is 1", 
				registrationService.registration(registrationData));
	}

	@Test
	public void registrationTestForPatient() {
		RegistrationData registrationData=new RegistrationData("abc", "abc", "male", "1998-07-22", "1234567890", 
				"aaaas@123", "123sas", "ROLE_PATIENT", "MS", "HEART", 123.4, "O+ve", 1, 1, 1);
		Date dateOfBirth=new DateUtil().convertToDate1("1998-07-22");
		User obj=new User(1, "abc", "abc", "male", dateOfBirth, "1234567890", 
				"aaaas@123", "123sas", "ROLE_DOCTOR", 1);
		
		registrationService.setObj(obj);
		when(constraintValidation.validationCheck(obj)).thenReturn("");		
		when(userRepository.save(obj)).thenReturn(obj);		
		Patient obj1=new Patient(1, "O+ve", obj);
		registrationService.setObj2(obj1);
		when(constraintValidation.validationCheck(obj1)).thenReturn("");
		when(patientRepository.save(obj1)).thenReturn(obj1);
		registrationService.setPatientRecord(patientRecord);
		Doctor obj2=new Doctor(1,"MS", "HEART",123.4,obj);
		when(docService.getDoctor(1)).thenReturn(obj2);		
		registrationService.setConsult(consult);
		when(patientRecord.getPatient()).thenReturn(obj1);
		registrationService.setConsultationService(consultationService);
		consult.setPatientId(obj1);
		consult.setDoctorId(obj2);
		consult.setDate(new Date());
		//when(consultationService.save(consult))
		when(prService.addPatientRecord(patientRecord)).thenReturn(patientRecord);
		assertEquals("Thanks For Registiring Please wait for the Approval Your UserId is 1", 
				registrationService.registration(registrationData));
	}
	
	@Test
	public void registrationTestForClerk() {
		RegistrationData registrationData=new RegistrationData("abc", "abc", "male", "1998-07-22", "1234567890", 
				"aaaas@123", "123sas", "ROLE_CLERK", "MS", "HEART", 123.4, "O+ve", 1, 1, 1);
		Date dateOfBirth=new DateUtil().convertToDate1("1998-07-22");
		User obj=new User(1, "abc", "abc", "male", dateOfBirth, "1234567890", 
				"aaaas@123", "123sas", "ROLE_CLERK", 1);
		registrationService.setObj(obj);
		when(constraintValidation.validationCheck(obj)).thenReturn("");		
		when(userRepository.save(obj)).thenReturn(obj);	
		Clerk obj1=new Clerk(1, obj); 
		registrationService.setObj3(obj1);
		when(clerkRepository.save(obj1)).thenReturn(obj1);
		assertEquals("Thanks For Registiring Please wait for the Approval Your UserId is 1", 
				registrationService.registration(registrationData));
	}
	
	@Test
	public void registrationTestForAdmin() {
		RegistrationData registrationData=new RegistrationData("abc", "abc", "male", "1998-07-22", "1234567890", 
				"aaaas@123", "123sas", "ROLE_ADMIN", "MS", "HEART", 123.4, "O+ve", 1, 1, 1);
		Date dateOfBirth=new DateUtil().convertToDate1("1998-07-22");
		User obj=new User(1, "abc", "abc", "male", dateOfBirth, "1234567890", 
				"aaaas@123", "123sas", "ROLE_ADMIN", 1);
		registrationService.setObj(obj);
		when(constraintValidation.validationCheck(obj)).thenReturn("");		
		when(userRepository.save(obj)).thenReturn(obj);	
		Admin obj1=new Admin(1, obj); 
		registrationService.setObj4(obj1);
		when(adminRepository.save(obj1)).thenReturn(obj1);
		assertEquals("Thanks For Registiring Your UserID is 1", 
				registrationService.registration(registrationData));
	}
	
	
	
}