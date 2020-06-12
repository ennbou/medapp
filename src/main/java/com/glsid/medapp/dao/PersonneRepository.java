package com.glsid.medapp.dao;

import com.glsid.medapp.modele.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long> {
    Personne findByCin(String cin);
}
