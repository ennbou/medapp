package com.glsid.medapp.service;

import org.springframework.data.domain.Page;

import com.glsid.medapp.modele.Patient;

public interface IPatientService {
	
	public Page<Patient> listPatient(String motCle, int page, int size);


}
