package com.glsid.medapp.entities;

import lombok.*;

import java.util.Collection;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


// TODO complete le DOC

/**
 * Medecin classe, c'est une  Entity qui herite l'Entity ou la classe {@link Personne}
 * <ul>
 *     <li>Il y a un seul medecin pour chaque {@link Specialite} </li>
 *     <li>Le medecin il y a plusieru {@link Consultation} </li>
 * </ul>
 */

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Table(name = Medecin.TABLE)
public class Medecin extends Personne {

    public static final String TABLE = "medecin";
    public static final String ID_F = "id_medecin";


    @ManyToOne
    @JoinColumn(name = Specialite.ID_F)
    @ToString.Exclude
    private Specialite specialite;

    @OneToMany(mappedBy = "medecin")
    @ToString.Exclude
    private Collection<Consultation> listConsultations;

    @OneToMany(mappedBy = "medecin")
    @ToString.Exclude
    private Collection<Exception> listExceptions;

    @Builder
    public Medecin(Long id, @NotBlank String cin, @NotBlank String prenom, @NotBlank String nom, @NotBlank String telephone, @NotBlank String email) {
        super(id, cin, prenom, nom, telephone, email);
    }

}