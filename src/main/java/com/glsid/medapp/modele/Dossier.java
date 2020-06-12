package com.glsid.medapp.modele;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

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
    private LocalDate dateCreation;


    @OneToMany(mappedBy = "dossier")
    private List<RendezVous> listRendezVous;

    @OneToOne
    private Patient patient;


}
