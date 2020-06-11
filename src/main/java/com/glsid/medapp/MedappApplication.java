package com.glsid.medapp;

import com.glsid.medapp.dao.*;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
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
			Patient p = Patient.builder().id(null).cin("12304" + i).prenom("prenom" + (i + 1)).nom("nom" + (i + 1))
					.telephone("06118912").email("email@email").address("Address " + i)
					.dateNaissance(LocalDate.now().minusYears(20 + i)).image("path").sexe(i % 2 == 0).build();
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
		Secretaire s1 = Secretaire.builder().cin("MC124364").prenom("prenom 1").nom("nom 1").telephone("0634853423")
				.email("nom1.prenom1@medapp.ma").build();
		Secretaire s2 = Secretaire.builder().cin("MC973497").prenom("prenom 2").nom("nom 2").telephone("0766041305")
				.email("nom2.prenom2@medapp.ma").build();

		secretaireRepository.save(s1);
		secretaireRepository.save(s2);
	}

	void initSpecialite() {
		List<Specialite> listSpecialite = new ArrayList<Specialite>();
		Specialite s1 = Specialite.builder().id(null).nom("Cardiologie").build();
		listSpecialite.add(s1);
		Specialite s2 = Specialite.builder().id(null).nom("Chirurgie générale").build();
		listSpecialite.add(s2);
		Specialite s3 = Specialite.builder().id(null).nom("Dermatologie").build();
		listSpecialite.add(s3);
		Specialite s4 = Specialite.builder().id(null).nom("Gastroentérologie").build();
		listSpecialite.add(s4);
		Specialite s5 = Specialite.builder().id(null).nom("Médecine dentaire").build();
		listSpecialite.add(s5);
		Specialite s6 = Specialite.builder().id(null).nom("Médecine générale").build();
		listSpecialite.add(s6);
		Specialite s7 = Specialite.builder().id(null).nom("Néphrologie").build();
		listSpecialite.add(s7);
		Specialite s8 = Specialite.builder().id(null).nom("Neurologie").build();
		listSpecialite.add(s8);
		Specialite s9 = Specialite.builder().id(null).nom("Ophtalmologie").build();
		listSpecialite.add(s9);
		Specialite s10 = Specialite.builder().id(null).nom("Oto-rhino-laryngologie").build();
		listSpecialite.add(s10);
		Specialite s11 = Specialite.builder().id(null).nom("Pédiatrie").build();
		listSpecialite.add(s11);
		Specialite s12 = Specialite.builder().id(null).nom("Pneumologie").build();
		listSpecialite.add(s12);
		Specialite s13 = Specialite.builder().id(null).nom("Psychiatrie").build();
		listSpecialite.add(s13);
		Specialite s14 = Specialite.builder().id(null).nom("Radiologie").build();
		listSpecialite.add(s14);
		Specialite s15 = Specialite.builder().id(null).nom("Réanimation Anésthésie").build();
		listSpecialite.add(s15);
		Specialite s16 = Specialite.builder().id(null).nom("Urologie").build();
		listSpecialite.add(s16);
		specialiteRepository.saveAll(listSpecialite);
	}

	void initMedecine() {
		specialiteRepository.findAll().forEach(spec -> {
			Medecin m = Medecin.builder().nom("nom" + (spec.getId()) + 1).cin("MC1243" + spec.getId())
					.prenom("prenom" + (spec.getId()) + 1).telephone("06347853423").email("email@medapp.ma").build();
			m.setSpecialite(spec);
			medecinRepository.save(m);
		});
	}

	void initRDV() {
		List<Specialite> specialites = specialiteRepository.findAll();
		
		secretaireRepository.findAll().forEach(sec -> {
			dossierRepository.findAll().forEach(dossier -> {
				RendezVous rdv = new RendezVous();
				rdv.setDescription("la description de RDV par le patient.");
				rdv.setDate(LocalDate.now().minusDays(10));
				int i= new Random().nextInt(specialites.size());
				rdv.setSpecialite(specialites.get(i));
				rdv.setSecretaire(sec);
				rdv.setDossier(dossier);
				rdv.setStatus(true);
				rendezVousRepository.save(rdv);
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
			c.setResultat("Ordance");
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
