package com.glsid.medapp.entities;

import java.util.Collection;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = Specialite.TABLE)
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Specialite {

    public static final String TABLE = "specialite";
    public static final String ID_F = "id_specialite";

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
	@Size(min=4,max=30)
    private String nom;

    @OneToMany(mappedBy = "specialite")
    private Collection<RendezVous> listRendezVous;

    @OneToMany(mappedBy = "specialite")
    private Collection<Medecin> listMedecins;
    
    @Builder
    public Specialite(Long id, @NotBlank String nom) {
    	this.id = id;
    	this.nom = nom;
    }


}
