package com.glsid.medapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glsid.medapp.dao.PatientRepository;
import com.glsid.medapp.modele.Patient;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Transactional
public class ImpPatientService implements IPatientService{

	@Autowired
	PatientRepository patientRepository;
	
	@Override
	public Page<Patient> listPatient(String motCle, int page, int size) {
		return patientRepository.listPatient(motCle, PageRequest.of(page, size));
	}

	@Override
	public void saveImage(MultipartFile imageFile) throws IOException {
		String medappPath = "/users/x/downloads/medappgit/";
		String folder = "src/main/resources/static/img/profiles/";
		byte[] bytes = imageFile.getBytes();
		Path path = Paths.get(medappPath + folder + imageFile.getOriginalFilename());
		Files.write(path, bytes);
	}

}
