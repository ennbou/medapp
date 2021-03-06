package com.glsid.medapp.dao;


import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.glsid.medapp.modele.Consultation;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation,Long>{
	
	// TODO : récupérer la liste en fonction de pagination
	public Page<Consultation> findAll(Pageable pageable);

	// TODO : liste des consultation entre deux date & recherche optionnelle
	@Query("SELECT c FROM Consultation c WHERE "
			+ "(c.rendezVous.dossier.patient.nom LIKE %:search% " 
			+ "OR c.rendezVous.dossier.patient.prenom LIKE %:search% "  
			+ "OR c.rendezVous.dossier.code LIKE %:search%)"
			+ "AND (c.date between :d1 and :d2 OR c.date between :d2 and :d1)")
    public Page<Consultation> searchUsingDate(@Param("search") String search,
    		@Param("d1") LocalDate first_date, 
    		@Param("d2") LocalDate second_date,Pageable pageable);

	// TODO : liste des consultation par un patient en fonction de ID
	@Query("SELECT c FROM Consultation c WHERE c.rendezVous.dossier.patient.id= :id")
    public Page<Consultation> searchUsingPatient(@Param("id") Long id, Pageable pageable);

	// TODO : liste des consultation par medecin en fonction de ID
	@Query("SELECT c FROM Consultation c WHERE c.medecin.id= :id")
    public Page<Consultation> searchUsingMedecin(@Param("id") Long id, Pageable pageable);
	
}
