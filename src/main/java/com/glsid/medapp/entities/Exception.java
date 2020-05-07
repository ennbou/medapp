package com.glsid.medapp.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Exception {

    @Id
    @GeneratedValue
    private Long id;
    private Date date;
    private String raison;

    @ManyToOne
    @JoinColumn(name = Medecin.ID_F)
    private Medecin medecin;

}
