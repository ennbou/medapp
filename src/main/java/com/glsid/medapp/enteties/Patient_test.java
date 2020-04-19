package com.glsid.medapp.enteties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Patient_test {
    @Id
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String numeroTelephone;
    private int image; // ex: PATH/dosiers/patients/id_nom_prenom/imgs/1.png
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date dateNaissance;
    private Boolean genre;  // true male false female ou le contrare

}
