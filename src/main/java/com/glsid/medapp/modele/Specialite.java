package com.glsid.medapp.modele;

import java.util.Collection;
import java.util.List;
import javax.persistence.*;
//<<<<<<< HEAD:src/main/java/com/glsid/medapp/entities/Specialite.java
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//=======
@Entity
@Table(name = Specialite.TABLE)
@Data
@NoArgsConstructor
@AllArgsConstructor
//>>>>>>> 00d610350c8ff80a53e018ccf4678346a9cf91ae:src/main/java/com/glsid/medapp/modele/Specialite.java
public class Specialite {

    public static final String TABLE = "specialite";
    public static final String ID_F = "id_specialite";

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @Size(min = 4, max = 30)
    private String nom;

    @OneToMany(mappedBy = "specialite")
    private List<RendezVous> listRendezVous;

    @OneToMany(mappedBy = "specialite", fetch = FetchType.EAGER)
    private List<Medecin> listMedecins;

    @Builder
    public Specialite(Long id, @NotBlank String nom) {
        this.id = id;
        this.nom = nom;
    }


}
