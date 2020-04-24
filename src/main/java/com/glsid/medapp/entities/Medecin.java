package com.glsid.medapp.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Medecin extends Personne{
	
	
	
	@ManyToOne
	@JoinColumn(name = "ID_SPECIALITE")
	private Specialite specialite;
	
	@OneToMany(mappedBy = "medecin")
	private Collection<Consultation> listConsultations;
	
	@OneToMany(mappedBy = "medecin")
	private Collection<Exception> listExceptions;

	

	public Medecin() {
		super();
	}

	public Medecin(String prenom, String nom, String telephone, String email) {
		super(prenom, nom, telephone, email);
	}
	
	public Medecin(String prenom, String nom, String telephone, String email, Specialite specialite) {
		super(prenom, nom, telephone, email);
		this.specialite = specialite;
	}

	
	public Specialite getSpecialite() {
		return specialite;
	}

	public void setSpecialite(Specialite specialite) {
		this.specialite = specialite;
	}

	public Collection<Consultation> getListConsultations() {
		return listConsultations;
	}

	public void setListConsultations(Collection<Consultation> listConsultations) {
		this.listConsultations = listConsultations;
	}
	
	

}