package com.glsid.medapp.modele;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


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
    @NotNull
    private String cin;
    @NotBlank
    @NotNull
    private String prenom;
    @NotBlank
    @NotNull
    private String nom;
    @NotBlank
    @NotNull
    private String telephone;
    @Email
    @NotNull
    private String email;

}
