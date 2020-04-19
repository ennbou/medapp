package com.example.demo.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class RendezVous implements Serializable{
	
	
	
	@Id @GeneratedValue
	private Long id;
	private Date date;
	private String description;
	@ManyToOne
	@JoinColumn(name = "ID_SECRETAIRE")
	private Secretaire secretaire;
	@ManyToOne
	@JoinColumn(name = "ID_MEDCIN")
	private Medecin medecin;
	
	@OneToOne(mappedBy = "rendezVous")
	private Consultation consultation;
	
	
	public RendezVous() {
		super();
	}


	public RendezVous(Date date, String description, Secretaire secretaire, Medecin medecin) {
		super();
		this.date = date;
		this.description = description;
		this.secretaire = secretaire;
		this.medecin = medecin;
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


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Secretaire getSecretaire() {
		return secretaire;
	}


	public void setSecretaire(Secretaire secretaire) {
		this.secretaire = secretaire;
	}


	public Medecin getMedecin() {
		return medecin;
	}


	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}


	public Consultation getConsultation() {
		return consultation;
	}


	public void setConsultation(Consultation consultation) {
		this.consultation = consultation;
	}
	
	
	
	
	
	
	
	
	

}
