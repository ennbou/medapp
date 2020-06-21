package com.glsid.medapp.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.glsid.medapp.modele.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    // TODO liste tous les patients
    public Page<Patient> findAll(Pageable pageable);

    @Query("SELECT p FROM Patient p WHERE p.nom LIKE %:motCle% or p.cin LIKE %:motCle%")
    public Page<Patient> listPatient(@Param("motCle") String motCle, Pageable pageable);
    
    @Query("SELECT p FROM Patient p WHERE p.cin LIKE %:cin%")
    public Patient search(@Param("cin") String cin);

}