package com.glsid.medapp;

import com.glsid.medapp.config.ROLE;
import com.glsid.medapp.dao.*;


import com.glsid.medapp.modele.*;
import com.glsid.medapp.modele.Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

// exclude = {SecurityAutoConfiguration.class}
@SpringBootApplication()
public class MedappApplication implements ApplicationRunner {

    private static PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

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

    public static PasswordEncoder getEncoder() {
    	return encoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(MedappApplication.class, args);
    }


    void initpatient() {
        for (int i = 0; i < 20; i++) {
            Patient p = Patient.builder().nom("nom" + (i + 1)).prenom("prenom" + (i + 1)).cin("MC12341" + i).
                    sexe(i % 2 == 0).telephone("06118912").email("patient" + i + "@medapp.ma")
                    .password(encoder.encode("pass")).roles(ROLE.ROLE_PATIENT.name()).address("adress 474").
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
                telephone("0634785342").email("secretaire1@medapp.ma").password(encoder.encode("pass"))
                .roles(ROLE.ROLE_SECRETAIRE.name()).build();
        Secretaire s2 = Secretaire.builder().cin("MC973497").prenom("prenom 2").nom("nom 2").
                telephone("0697495651").email("secretaire2@medapp.ma").password(encoder.encode("pass"))
                .roles(ROLE.ROLE_SECRETAIRE.name()).build();

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
            Medecin m = Medecin.builder().nom("nom " + (spec.getId()) + 1).cin("MC12430" + spec.getId())
            		.prenom("prenom " + (spec.getId()) + 1).telephone("063478534")
            		.email("medecin" + spec.getId() + "@medapp.ma").password(encoder.encode("pass"))
                    .roles(ROLE.ROLE_ADMIN.name() + "," + ROLE.ROLE_SECRETAIRE.name()).build();
            m.setSpecialite(spec);
            medecinRepository.save(m);
        });
    }

    void initRDV() {
        specialiteRepository.findAll().forEach(spec -> {
                dossierRepository.findAll().forEach(dossier -> {
                    RendezVous rdv = new RendezVous();
                    rdv.setDescription("la description de RDV par le patient.");
                    rdv.setDate(LocalDateTime.now().minusDays(10));
                    rdv.setSpecialite(spec);
                    rdv.setDossier(dossier);
                    rendezVousRepository.save(rdv);
                });
        });
    }

    void initConsultation() {
    	List<Medecin> medecins =medecinRepository.findAll();
        rendezVousRepository.findAll().forEach(rdv -> {
        	int i=new Random().nextInt(medecins.size());
            Consultation c = new Consultation();
            c.setDate(LocalDate.now());
            c.setHeure_debut(LocalTime.now());
            c.setResultat("exemple");
            c.setHeure_fin(LocalTime.now().plusMinutes(10));
            c.setMedecin(medecins.get(i));
            c.setRendezVous(rdv);
            consultationRepository.save(c);
        });
    }

    void initException() {
        medecinRepository.findAll().forEach(medecin -> {
            Exception e = new Exception();
            e.setDate(LocalDate.now().plusDays(20));
            e.setRaison("Raison x");
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
