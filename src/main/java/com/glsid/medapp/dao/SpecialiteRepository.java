package com.glsid.medapp.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.glsid.medapp.modele.Specialite;

public interface SpecialiteRepository extends JpaRepository<Specialite, Long>{
	         
	public Page<Specialite> findByNom(String mc, Pageable pageable);
	
	//TODO  :  par Nom specialite ...
	@Query("SELECT s FROM Specialite s WHERE s.nom LIKE %:motCle% ")
	public Page<Specialite> listSpecialite(@Param("motCle") String motCle, Pageable pageable);
}
