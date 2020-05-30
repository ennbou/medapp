package com.glsid.medapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.glsid.medapp.modele.Medecin;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin, Long> {

}
