package com.glsid.medapp.modele;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Consultation {


    @Id @GeneratedValue
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date date;
    @Temporal(TemporalType.TIME)
    private Date heure_debut;
    @Temporal(TemporalType.TIME)
    private Date heure_fin;
    
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