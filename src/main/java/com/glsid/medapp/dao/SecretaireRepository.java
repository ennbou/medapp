package com.glsid.medapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glsid.medapp.entities.Secretaire;
import org.springframework.stereotype.Repository;

@Repository
public interface SecretaireRepository extends JpaRepository<Secretaire, Long> {

}
