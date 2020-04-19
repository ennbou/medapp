package com.example.demo.entities;

import java.time.LocalTime;
import java.util.Date;

import javax.persistence.CascadeType;
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
	private Date date;
	private LocalTime date_debut;
	private LocalTime date_fin;
	
	
	@OneToOne
	@JoinColumn(name = "RENDEZ_VOUS_ID")
	private RendezVous rendezVous;
	
	@ManyToOne
	@JoinColumn(name = "CODE_DOSSIER")
	private Dossier dossier;

	public Consultation() {
		super();
	}

	public Consultation(Date date, LocalTime date_debut, LocalTime date_fin, RendezVous rendezVous) {
		super();
		this.date = date;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.rendezVous = rendezVous;
	}
	
	

	public Consultation(Date date, RendezVous rendezVous) {
		super();
		this.date = date;
		this.rendezVous = rendezVous;
	}
	
	

	public Consultation(Date date) {
		super();
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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
