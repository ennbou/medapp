package com.glsid.medapp.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.glsid.medapp.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{

	@Query("SELECT p FROM Patient p WHERE p.nom LIKE %:motCle%")
	public Page<Patient> listPatient(@Param("motCle")String motCle, Pageable pageable);
}