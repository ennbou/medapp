package com.glsid.medapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glsid.medapp.dao.PatientRepository;
import com.glsid.medapp.modele.Patient;

@Service
@Transactional
public class ImpPatientService implements IPatientService{

	@Autowired
	PatientRepository patientRepository;
	
	@Override
	public Page<Patient> listPatient(String motCle, int page, int size) {
		return patientRepository.listPatient(motCle, PageRequest.of(page, size));
	}

}
