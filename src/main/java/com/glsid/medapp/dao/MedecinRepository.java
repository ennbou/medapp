package com.glsid.medapp.dao;

<<<<<<< HEAD
import com.glsid.medapp.entities.Medecin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

=======
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.glsid.medapp.modele.Medecin;

>>>>>>> 00d610350c8ff80a53e018ccf4678346a9cf91ae
@Repository
public interface MedecinRepository extends JpaRepository<Medecin, Long> {
	public Page<Medecin> findByNom(String mc, Pageable pageable);
}
