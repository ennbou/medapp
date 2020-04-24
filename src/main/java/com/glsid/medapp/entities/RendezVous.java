package com.glsid.medapp.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class RendezVous{
	
	
	
	@Id @GeneratedValue
	private Long id;
	private Date date;
	private String description;
	@ManyToOne
	@JoinColumn(name = "ID_SECRETAIRE")
	private Secretaire secretaire;
	@ManyToOne
	@JoinColumn(name = "ID_SPECIALITE")
	private Specialite specialite;
	
	
	@OneToOne(mappedBy = "rendezVous")
	private Consultation consultation;
	
	
	public RendezVous() {
		super();
	}


	public RendezVous(Date date, String description, Secretaire secretaire, Specialite specialite) {
		super();
		this.date = date;
		this.description = description;
		this.secretaire = secretaire;
		this.specialite = specialite;
		
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


	


	public Consultation getConsultation() {
		return consultation;
	}


	public void setConsultation(Consultation consultation) {
		this.consultation = consultation;
	}
	
	
	
	
	
	
	
	
	

}