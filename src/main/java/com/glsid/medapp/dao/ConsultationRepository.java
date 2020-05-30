package com.glsid.medapp.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.glsid.medapp.entities.Consultation;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation,Long>{

	@Query("SELECT c FROM Consultation c WHERE c.id=motCle")
	public Page<Consultation> getConsultByID(@Param("motCle")Long motCle, Pageable pageable);	

	// TODO liste des consultation entre deux date

	// TODO liste des consultation par un patient

	// TODO liste des consultation par medecin
}
