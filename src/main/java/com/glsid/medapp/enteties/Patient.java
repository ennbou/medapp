package com.example.demo.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Patient extends Personne{
	
	private Boolean sexe;
	private String address;
	private Date dateNaissance;
	private String image;
	
	
	@OneToOne(mappedBy = "patient")
	private Dossier dossier;
	
	
	
	
	public Patient() {
		super();
	}




	public Patient(String prenom, String nom, String telephone, String email, Boolean sexe, String address,
			Date dateNaissance, String image) {
		super(prenom, nom, telephone, email);
		this.sexe = sexe;
		this.address = address;
		this.dateNaissance = dateNaissance;
		this.image = image;
	}




	public Boolean getSexe() {
		return sexe;
	}




	public void setSexe(Boolean sexe) {
		this.sexe = sexe;
	}




	public String getAddress() {
		return address;
	}




	public void setAddress(String address) {
		this.address = address;
	}




	public Date getDateNaissance() {
		return dateNaissance;
	}




	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}




	public String getImage() {
		return image;
	}




	public void setImage(String image) {
		this.image = image;
	}




	




	




	
	
	
	
	
	
	
	
	
	

}
