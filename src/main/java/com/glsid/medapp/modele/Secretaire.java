package com.glsid.medapp.modele;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@ToString
@Table(name = Secretaire.TABLE)
public class Secretaire extends Personne {

    public static final String TABLE = "secretaire";
    public static final String ID_F = "id_secretaire";

    @Builder
    public Secretaire(Long id, @NotBlank String cin, @NotBlank String prenom, @NotBlank String nom, @NotBlank String telephone, @NotBlank String email, String password, String roles) {
        super(id, cin, prenom, nom, telephone, email, password, roles);
    }
}
