package com.glsid.medapp.modele;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = Dossier.TABLE)
public class Dossier {

    public static final String TABLE = "Dossier";
    public static final String ID_F = "id_dossier";

    @Id
    private Long code;
    private Date dateCreation;


    @OneToMany(mappedBy = "dossier")
    private Collection<Consultation> listConsultations;

    @OneToOne
    @JoinColumn(name = Patient.ID_F)
    private Patient patient;


}
