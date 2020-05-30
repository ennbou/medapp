package com.glsid.medapp.modele;

import java.util.Collection;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = Specialite.TABLE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Specialite {

    public static final String TABLE = "specialite";
    public static final String ID_F = "id_specialite";

    @Id
    @GeneratedValue
    private Long id;
    private String nom;

    @OneToMany(mappedBy = "specialite")
    private Collection<RendezVous> listRendezVous;

    @OneToMany(mappedBy = "specialite")
    private Collection<Medecin> listMedecins;


}
