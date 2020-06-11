package com.glsid.medapp.dao;

import com.glsid.medapp.modele.Medecin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin, Long> {
	public Page<Medecin> findByNom(String mc, Pageable pageable);
	
	 @Query("SELECT m FROM Medecin m WHERE m.nom LIKE %:motCle% or m.cin LIKE %:motCle%")
	 public Page<Medecin> listMedecin(@Param("motCle") String motCle, Pageable pageable);

}
