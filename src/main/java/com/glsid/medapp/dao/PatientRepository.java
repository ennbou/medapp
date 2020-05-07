package com.glsid.medapp.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.glsid.medapp.entities.Patient;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    // TODO liste tous les patients
    public Page<Patient> findAll(Pageable pageable);

    @Query("SELECT p FROM Patient p WHERE p.nom LIKE %:motCle%")
    public Page<Patient> listPatient(@Param("motCle") String motCle, Pageable pageable);
    // TODO  la liste des patiente by nom
    // Page<Patient> findByNomContaining(String nom, Pageable pageable);



}