package com.glsid.medapp.PatientService;

import org.springframework.data.domain.Page;

import com.glsid.medapp.enteties.Patient;

public interface IPatientService {
	
	public Page<Patient> listPatient(String motCle, int page, int size);


}