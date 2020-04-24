package com.glsid.medapp.enteties;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Patient extends Personne{
	
	@NotNull
	private Boolean sexe;
	@NotBlank
	private String address;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
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
