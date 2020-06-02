package com.glsid.medapp;

import com.glsid.medapp.dao.*;

//>>>>>>> 00d610350c8ff80a53e018ccf4678346a9cf91ae
import com.glsid.medapp.modele.*;
import com.glsid.medapp.modele.Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.time.LocalDate;
import java.time.LocalTime;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class MedappApplication implements ApplicationRunner {

    @Autowired
    PatientRepository patientRepository;
    @Autowired
    MedecinRepository medecinRepository;
    @Autowired
    RendezVousRepository rendezVousRepository;
    @Autowired
    SpecialiteRepository specialiteRepository;
    @Autowired
    DossierRepository dossierRepository;
    @Autowired
    SecretaireRepository secretaireRepository;
    @Autowired
    ConsultationRepository consultationRepository;
    @Autowired
    ExceptionRepository exceptionRepository;


    public static void main(String[] args) {
        SpringApplication.run(MedappApplication.class, args);
    }


    void initpatient() {
        for (int i = 0; i < 20; i++) {
            Patient p = Patient.builder().nom("nom" + (i + 1)).prenom("prenom" + (i + 1)).
                    sexe(i % 2 == 0).telephone("06118912").email("email@email").
                    dateNaissance(LocalDate.now().minusYears(20 + i)).image("path").build();
            patientRepository.save(p);

        }
    }

    void initDossier() {
        patientRepository.findAll().forEach(p -> {
            Dossier d = new Dossier();
            d.setCode(p.getId() * 10);
            d.setPatient(p);
            d.setDateCreation(LocalDate.now().minusDays(p.getId()));
            dossierRepository.save(d);
        });

    }

    void initSecretaire() {
        Secretaire s1 = Secretaire.builder().cin("MC1243").prenom("prenom 1").nom("nom 1").
                telephone("06347853423").email("email@medapp.ma").build();
        Secretaire s2 = Secretaire.builder().cin("MC973497").prenom("prenom 2").nom("nom 2").
                telephone("069749").email("email2@medapp.ma").build();

        secretaireRepository.save(s1);
        secretaireRepository.save(s2);

    }

    void initSpecialite() {
        Specialite s1 = Specialite.builder().nom("specialite 1").build();
        Specialite s2 = Specialite.builder().nom("specialite 2").build();
        Specialite s3 = Specialite.builder().nom("specialite 3").build();

        specialiteRepository.save(s1);
        specialiteRepository.save(s2);
        specialiteRepository.save(s3);
    }

    void initMedecine() {
        specialiteRepository.findAll().forEach(spec -> {
            Medecin m = Medecin.builder().nom("nom "+(spec.getId())+1).cin("MC1243" + spec.getId()).prenom("prenom "+(spec.getId())+1).
                    telephone("06347853423").email("email@medapp.ma").build();
            m.setSpecialite(spec);
            medecinRepository.save(m);
        });
    }

    void initRDV() {
        specialiteRepository.findAll().forEach(spec -> {
            secretaireRepository.findAll().forEach(sec -> {
                dossierRepository.findAll().forEach(dossier -> {
                    RendezVous rdv = new RendezVous();
                    rdv.setDescription("la description de RDV par le patient.");
                    rdv.setDate(LocalDate.now().minusDays(10));
                    rdv.setSpecialite(spec);
                    rdv.setSecretaire(sec);
                    rdv.setDossier(dossier);
                    rendezVousRepository.save(rdv);
                });

            });
        });
    }

    void initConsultation() {
        rendezVousRepository.findAll().forEach(rdv -> {
            Consultation c = new Consultation();
            c.setDate(LocalDate.now());
            c.setHeure_debut(LocalTime.now());
            c.setHeure_fin(LocalTime.now().plusHours(1));
            c.setMedecin(rdv.getSpecialite().getListMedecins().get(0));
            c.setRendezVous(rdv);
            consultationRepository.save(c);
        });
    }

    void initException() {
        medecinRepository.findAll().forEach(medecin -> {
            Exception e = new Exception();
            e.setDate(LocalDate.now().plusDays(20));
            e.setRaison("pour une raison");
            e.setMedecin(medecin);
            exceptionRepository.save(e);
        });
    }


    @Override
    public void run(ApplicationArguments args) throws java.lang.Exception {
        initpatient();
        initDossier();
        initSecretaire();
        initSpecialite();
        initMedecine();
        initRDV();
        initConsultation();
        initException();
    }
}
