package com.glsid.medapp.temp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;

import com.glsid.medapp.modele.Consultation;
import com.glsid.medapp.modele.Dossier;
import com.glsid.medapp.modele.Medecin;
import com.glsid.medapp.modele.RendezVous;

final public class Data2 {

    public static Consultation consult;
    public static Medecin medecin = null;
    public static Dossier dossier = null;
    public static RendezVous rendezVous = null;

    static {
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {

            medecin =new Medecin();
            dossier=new Dossier();
            rendezVous=new RendezVous();

            consult = new Consultation(null, LocalDate.of(2020, 05, 10), LocalTime.of(12, 0),
                    LocalTime.of(13, 0), "ASPRO + POMADA SAFRA", rendezVous, medecin);
        } catch (Exception e) {
        }
    }


}
