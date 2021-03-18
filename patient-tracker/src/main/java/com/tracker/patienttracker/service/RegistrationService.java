package com.tracker.patienttracker.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

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

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Service
public class RegistrationService {

	@Autowired
	RegistrationData registrationData; 
	@Autowired
	UserRepository userRepository;
	@Autowired
	AdminRepository adminRepository;
	@Autowired
	DoctorRepository doctorRepository;
	@Autowired
	PatientRepository patientRepository;
	@Autowired
	ClerkRepository clerkRepository;
	@Autowired
	ConstraintValidation constraintValidation;
	@Autowired
	PatientRecordService prService;
	@Autowired
	User obj;
	@Autowired
	Doctor obj1;
	@Autowired
	Patient obj2;
	@Autowired
	Clerk obj3;
	@Autowired
	Admin obj4;
	@Autowired
	PatientRecord patientRecord;
	@Autowired
	ConsultationService consultationService;
	@Autowired
	Consultation consult;
	
	@Autowired
	DoctorService docService;
	
	@Transactional
	public String registration(RegistrationData registrationData) {		
		String firstName=registrationData.getFirstName();
		String lastName=registrationData.getLastName();
		String gender=registrationData.getGender();
		String dob=registrationData.getDateOfBirth();
		Date dateOfBirth=new DateUtil().convertToDate1(dob);
		String contactNo=registrationData.getContactNo();
		String password=registrationData.getPassword();
		String address=registrationData.getAddress();
		String role=registrationData.getRole();
		int doctorId = registrationData.getDoctorId();
		obj.setFirstName(firstName);
		obj.setLastName(lastName);
		obj.setGender(gender);
		obj.setDateOfBirth(dateOfBirth);
		obj.setContactNo(contactNo);
		obj.setPassword(password);
		obj.setAddress(address);
		obj.setRole(role);	
		if(registrationData.getUserId()!=-1)
			obj.setUserId(registrationData.getUserId());
		
		System.out.println(registrationData);
		String errors=constraintValidation.validationCheck(obj);
		System.out.println(errors);
		if(!errors.equals(""))
			return errors; 
		
		if(role.equals("ROLE_ADMIN"))
			obj.setApproved(1);
		
		int userId=0;
		User userObj=userRepository.save(obj);
		if(role.equals("ROLE_DOCTOR")) {		
			userId=userObj.getUserId();
			obj1.setDoctorId(userId);
			obj1.setQualification(registrationData.getQualification());
			obj1.setSpecialization(registrationData.getSpecialization());
			obj1.setConsultationFee(registrationData.getConsultationFee());
			errors=constraintValidation.validationCheck(obj1);
			if(!errors.equals(""))
				return errors; 
			doctorRepository.save(obj1);
			}
		else if(role.equals("ROLE_PATIENT")) {
			userId=userObj.getUserId();
			obj2.setPatientId(userId);
			obj2.setBloodGroup(registrationData.getBloodGroup());
			obj2.setUser(userObj);			
			errors=constraintValidation.validationCheck(obj2);
			if(!errors.equals(""))
				return errors; 
			patientRepository.save(obj2);
			
			patientRecord.setPatient(obj2);
			patientRecord.setRecordId(obj2.getPatientId());
			patientRecord.setDate(new Date());			
			Doctor doctor = docService.getDoctor(doctorId);
			patientRecord.setDoctor(doctor);
			
			consult.setPatientId(patientRecord.getPatient());
			consult.setDoctorId(doctor);
			consult.setDate(new Date());
			
			consultationService.save(consult);
			prService.addPatientRecord(patientRecord);
		}
		else if(role.equals("ROLE_CLERK")){
			userId=userObj.getUserId();
			obj3.setClerkId(userId);
			obj3.setUser(userObj);
			clerkRepository.save(obj3);			
		}
		else if(role.equals("ROLE_ADMIN")){
			userId=userObj.getUserId();
			obj4.setAdminId(1);
			obj4.setUser(userObj);
			adminRepository.save(obj4);
			return "Thanks For Registiring Your UserID is "+userId;
		}
		return "Thanks For Registiring Please wait for the Approval Your UserId is "+userId;
	}
	
}

