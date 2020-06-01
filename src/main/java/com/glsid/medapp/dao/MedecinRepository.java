package com.glsid.medapp.dao;

import com.glsid.medapp.entities.Medecin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin, Long> {
	public Page<Medecin> findByNom(String mc, Pageable pageable);
}
