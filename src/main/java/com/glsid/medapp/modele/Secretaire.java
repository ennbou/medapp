package com.glsid.medapp.modele;

import lombok.*;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = Secretaire.TABLE)
public class Secretaire extends Personne {

    public static final String TABLE = "secretaire";
    public static final String ID_F = "id_secretaire";

    @OneToMany(mappedBy = "secretaire", fetch = FetchType.LAZY)
    private Collection<RendezVous> listRendezVous;

    @Builder
    public Secretaire(Long id, @NotBlank String cin, @NotBlank String prenom, @NotBlank String nom, @NotBlank String telephone, @NotBlank String email, String password, String roles) {
        super(id, cin, prenom, nom, telephone, email, password, roles);
    }
}
