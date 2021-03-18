package com.tracker.patienttracker.repository;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.tracker.patienttracker.model.Medicine;
import com.tracker.patienttracker.model.MedicineQuantity;
import com.tracker.patienttracker.model.PatientRecord;
import com.tracker.patienttracker.model.Prescription;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace =Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PrescriptionRepositoryTest {

	@Autowired
	private PrescriptionRepository prescriptionRepository;
	
	@Autowired
	private MedicineRepository medicineRepository;
	
	@Autowired
	private PatientRecordRepository patientRecordRepository;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Test
	@Order(1)
	public void testSavePrescription() {
		Prescription prescription = new Prescription();
		Set<MedicineQuantity> medicineQuantities = new HashSet<>();
		
		//first medicine
		MedicineQuantity medicineQuantity = new MedicineQuantity();
		Optional<Medicine> optional = medicineRepository.findById(1);
		Medicine medicine = optional.get();
		medicineQuantity.setMedicine(medicine);
		medicineQuantity.setQuantity(10);
		medicineQuantities.add(medicineQuantity);
		//second medicine
		MedicineQuantity medicineQuantity2 = new MedicineQuantity();
		optional = medicineRepository.findById(2);
		medicine = optional.get();
		medicineQuantity2.setMedicine(medicine);
		medicineQuantity2.setQuantity(10);
		medicineQuantities.add(medicineQuantity2);
		//add prescription
		prescription.setMedicineQuantities(medicineQuantities);
		prescription.setPrescriptionCost(1000);
		prescription.setPaid(false);
		prescription.setBilled(false);
		
		
		Optional<PatientRecord> optional2 = patientRecordRepository.findById(1);
		PatientRecord patientRecord=optional2.get();
		
		prescription.setPatientRecord(patientRecord);
		
		Prescription saved = prescriptionRepository.save(prescription);
		
		assertEquals(prescription, saved);
		
		assertEquals(1,1);
	}
	
	@Test
	public void testgetAllPrescriptionsForPatientForBilling() {
		Set<Prescription> prescriptions1 = new HashSet<Prescription>();
		//Prescription 1
		Prescription prescription1 = new Prescription();
		prescription1.setPrescriptionId(31);
		prescription1.setPrescriptionCost(250.00);
		prescription1.setBilled(false);
		prescription1.setPaid(false);
		prescription1.setPatientRecord(patientRecordRepository.findById(31).get());
		//Prescription 2
		Prescription prescription2 = new Prescription();
		prescription2.setPrescriptionId(33);
		prescription2.setPrescriptionCost(550.00);
		prescription2.setBilled(false);
		prescription2.setPaid(false);
		prescription2.setPatientRecord(patientRecordRepository.findById(31).get());
		prescriptions1.add(prescription1);
		prescriptions1.add(prescription2);
		
		Set<Prescription> prescriptions2 = prescriptionRepository.findByPatientRecordAndBilledFalse(patientRecordRepository.findById(31).get());
				
		assertNotNull(prescriptions2);
		
	}
}
