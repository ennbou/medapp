package com.glsid.medapp.service;

import org.springframework.data.domain.Page;

import com.glsid.medapp.modele.Patient;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IPatientService {
	
	public Page<Patient> listPatient(String motCle, int page, int size);
	public void saveImage(MultipartFile imageFile) throws IOException;


}
