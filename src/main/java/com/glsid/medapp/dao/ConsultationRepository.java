package com.glsid.medapp.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.glsid.medapp.modele.Consultation;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation,Long>{

}
