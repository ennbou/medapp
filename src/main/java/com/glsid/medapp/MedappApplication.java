package com.glsid.medapp;

import com.glsid.medapp.dao.MedecinRepository;
import com.glsid.medapp.dao.PatientRepository;
import com.glsid.medapp.dao.RendezVousRepository;
<<<<<<< HEAD
import com.glsid.medapp.dao.SpecialiteRepository;
import com.glsid.medapp.entities.Medecin;
import com.glsid.medapp.entities.Patient;
import com.glsid.medapp.entities.RendezVous;
import com.glsid.medapp.entities.Specialite;

=======
>>>>>>> 00d610350c8ff80a53e018ccf4678346a9cf91ae
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class MedappApplication implements ApplicationRunner {

    @Autowired
    PatientRepository repoPatient;

    @Autowired
    MedecinRepository repoMedecin;
    @Autowired
    RendezVousRepository rendezVousRepository;
    @Autowired
    SpecialiteRepository specialiteRepository;

    public static void main(String[] args) {
        SpringApplication.run(MedappApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        Patient p1 = Patient.builder().id((long) 1).nom("nom1").prenom("prenom1").
//                sexe(true).telephone("0611").email("email@email").
//                dateNaissance(LocalDate.of(2020, 10, 1)).image("path").build();
//
//        Medecin m1 = Medecin.builder().id((long) 1).nom("nom1").prenom("prenom1").
//                telephone("0611").email("email@email").build();

//        Medecin m2 = new Medecin((long) 1, "pr", "nom", "000", "email");

//        System.out.println(p1);
//        System.out.println(m1);
//        System.out.println(m2);

//        repoPatient.save(p1);

//        repoMedecin.save(m1);
//        repoMedecin.save(m2);   			
    }
}
