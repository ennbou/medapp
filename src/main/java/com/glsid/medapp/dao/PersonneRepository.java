package com.glsid.medapp.dao;

import com.glsid.medapp.modele.Patient;
import com.glsid.medapp.modele.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long> {
    Personne findByEmail(String email);
}
