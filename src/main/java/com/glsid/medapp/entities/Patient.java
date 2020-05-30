package com.glsid.medapp.entities;

import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Getter
@Setter
@Table(name = Patient.TABLE)
public class Patient extends Personne {

    public static final String TABLE = "Patient";
    public static final String ID_F = "id_patient";


    @NotBlank
    private String address;
    @NotNull
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateNaissance;
    private String image;
    @NotNull
    private Boolean sexe;

    @OneToOne(mappedBy = "patient")
    @ToString.Exclude
    private Dossier dossier;

    @Builder
    public Patient(Long id, @NotBlank String cin, @NotBlank String prenom, @NotBlank String nom, @NotBlank String telephone, @NotBlank String email, String address, LocalDate dateNaissance, String image, Boolean sexe) {
        super(id, cin, prenom, nom, telephone, email);
        this.address = address;
        this.dateNaissance = dateNaissance;
        this.image = image;
        this.sexe = sexe;
    }

}
