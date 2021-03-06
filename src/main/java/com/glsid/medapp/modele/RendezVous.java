package com.glsid.medapp.modele;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDateTime;


import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RendezVous {

    public static final String TABLE = "rendez_vous";
    public static final String ID_F = "id_rendez_vous";

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime date; // date creation
    private String description;

    @ManyToOne
    @JoinColumn(name = Specialite.ID_F)
    private Specialite specialite;


    @OneToOne(mappedBy = "rendezVous")
    private Consultation consultation;

    @ManyToOne
    @JoinColumn(name = Dossier.ID_F)
    private Dossier dossier;

}
