package com.example.demo.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Dossier {

	
	@Id
	private Long code;
	
	
	@OneToMany(mappedBy = "dossier")
    private Collection<Consultation> listConsultations;
	
	@OneToOne
	@JoinColumn(name = "ID_PATIENT")
	private Patient patient;

	public Dossier() {
		super();
	}

	public Dossier(Long code, Patient patient) {
		super();
		this.code = code;
		this.patient = patient;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public Collection<Consultation> getListConsultations() {
		return listConsultations;
	}

	public void setListConsultations(Collection<Consultation> listConsultations) {
		this.listConsultations = listConsultations;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	
	
	
}