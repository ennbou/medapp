package com.glsid.medapp.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.glsid.medapp.entities.RendezVous;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {

}
