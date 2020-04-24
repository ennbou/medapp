/**
 * pour la liste des RDVs
 */

package com.glsid.medapp.temp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RDV {

    private Long id;
    private LocalDateTime dateCreation;
    private LocalDate dateRDV;
    private String patient;
    private String specialite;
    private String medecine;
    private String description;
    private Boolean statut;

}
