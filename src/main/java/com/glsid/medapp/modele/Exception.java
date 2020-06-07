package com.glsid.medapp.modele;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exception {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDate date;
    private String raison;

    @ManyToOne
    @JoinColumn(name = Medecin.ID_F)
    private Medecin medecin;

}
