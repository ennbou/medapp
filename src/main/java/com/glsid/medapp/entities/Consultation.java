package com.glsid.medapp.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class Consultation {

	
	@Id @GeneratedValue
	private Long id;
	private LocalDate date;
	private LocalTime date_debut;
	private LocalTime date_fin;
	private String resultat;
	
	
	@OneToOne
	@JoinColumn(name = "RENDEZ_VOUS_ID")
	private RendezVous rendezVous;
	
	@ManyToOne
	@JoinColumn(name = "ID_MEDCIN")
	private Medecin medecin;
	
	@ManyToOne
	@JoinColumn(name = "CODE_DOSSIER")
	private Dossier dossier;
	
	//TEMPORAIRE
	public Consultation(Long id, LocalDate date, LocalTime date_debut, LocalTime date_fin, String resultat) {
		this.id=id;
		this.date = date;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.resultat = resultat;
	}

	public Consultation(LocalDate date, LocalTime date_debut, LocalTime date_fin, RendezVous rendezVous, String resultat) {
		this.date = date;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.rendezVous = rendezVous;
		this.resultat = resultat;
	}
	
	

	public String getResultat() {
		return resultat;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}

	public Consultation(LocalDate date, RendezVous rendezVous) {
		super();
		this.date = date;
		this.rendezVous = rendezVous;
	}
	
	

	public Consultation(LocalDate date) {
		super();
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getDate_debut() {
		return date_debut;
	}

	public void setDate_debut(LocalTime date_debut) {
		this.date_debut = date_debut;
	}

	public LocalTime getDate_fin() {
		return date_fin;
	}

	public void setDate_fin(LocalTime date_fin) {
		this.date_fin = date_fin;
	}

	public RendezVous getRendezVous() {
		return rendezVous;
	}

	public void setRendezVous(RendezVous rendezVous) {
		this.rendezVous = rendezVous;
	}

	public Dossier getDossier() {
		return dossier;
	}

	public void setDossier(Dossier dossier) {
		this.dossier = dossier;
	}
	
	
	
    
	
	
}