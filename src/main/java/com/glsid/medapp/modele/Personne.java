package com.glsid.medapp.modele;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Personne {


    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    @Column(unique = true)
    private String cin;
    @NotBlank
    private String prenom;
    @NotBlank
    private String nom;
    @NotBlank
    private String telephone;
    @Email
    private String email;

}
