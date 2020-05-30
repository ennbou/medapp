package com.glsid.medapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Consultation {


    @Id
    @GeneratedValue
    private Long id;
    private LocalDate date;
    private LocalTime heure_debut;
    private LocalTime heure_fin;
    private String resultat;


    @OneToOne
    @JoinColumn(name = RendezVous.ID_F)
    private RendezVous rendezVous;

    @ManyToOne
    @JoinColumn(name = Medecin.ID_F)
    private Medecin medecin;

    @ManyToOne
    @JoinColumn(name = Dossier.ID_F)
    private Dossier dossier;


}