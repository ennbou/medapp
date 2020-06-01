package com.glsid.medapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.glsid.medapp.modele.Secretaire;

@Repository
public interface SecretaireRepository extends JpaRepository<Secretaire, Long> {

}
