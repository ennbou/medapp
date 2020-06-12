package com.glsid.medapp.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.glsid.medapp.modele.RendezVous;
import java.util.List;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {

    @Query("SELECT rdv FROM RendezVous rdv WHERE (:nom = '' OR rdv.dossier.patient.nom = :nom) " +
            "AND (:prenom = '' OR rdv.dossier.patient.prenom = :prenom) " +
            "AND rdv.specialite.nom IN (:sp)")
    List<RendezVous> findAllAdvencedSearch(@Param("nom") String nom, @Param("prenom") String prenom, @Param("sp") List<String> specialites);

    @Query("SELECT rdv FROM RendezVous rdv WHERE (:nom IS NULL OR rdv.dossier.patient.nom = :nom) " +
            "AND (:prenom IS NULL OR rdv.dossier.patient.prenom = :prenom) ")
    List<RendezVous> findAllAdvencedSearch(@Param("nom") String nom, @Param("prenom") String prenom);

}
