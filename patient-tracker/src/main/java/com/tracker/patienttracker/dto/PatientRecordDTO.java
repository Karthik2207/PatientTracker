package com.tracker.patienttracker.dto;

import java.util.Set;

import com.tracker.patienttracker.model.Doctor;
import com.tracker.patienttracker.model.Prescription;
import com.tracker.patienttracker.model.TestReport;
import com.tracker.patienttracker.model.Treatment;

public class PatientRecordDTO {

	private int doctorId;
	
	private int RecordId;
	
	private Prescription prescription;
	
	private Treatment treatment;
	
	private TestReport testReport;

	public TestReport getTestReport() {
		return testReport;
	}

	public void setTestReport(TestReport testreport) {
		this.testReport = testreport;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public int getRecordId() {
		return RecordId;
	}

	public void setRecordId(int recordId) {
		RecordId = recordId;
	}

	public Prescription getPrescription() {
		return prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}

	public Treatment getTreatment() {
		return treatment;
	}

	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
	}

	
	
}
