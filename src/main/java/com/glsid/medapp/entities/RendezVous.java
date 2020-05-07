package com.glsid.medapp.entities;

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


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RendezVous {

    public static final String TABLE = "rendez_vous";
    public static final String ID_F = "id_rendez_vous";

    @Id
    @GeneratedValue
    private Long id;
    private Date date;
    private String description;

    @ManyToOne
    @JoinColumn(name = Secretaire.ID_F)
    private Secretaire secretaire;

    @ManyToOne
    @JoinColumn(name = Specialite.ID_F)
    private Specialite specialite;


    @OneToOne(mappedBy = "rendezVous")
    private Consultation consultation;

}