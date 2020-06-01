package com.glsid.medapp.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.glsid.medapp.modele.Specialite;

public interface SpecialiteRepository extends JpaRepository<Specialite, Long>{
	          public Page<Specialite> findByNom(String mc, Pageable pageable);
}
