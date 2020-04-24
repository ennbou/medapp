package com.glsid.medapp.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotBlank;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Personne{

	
	@Id @GeneratedValue
	private Long id;
	@NotBlank
	private String prenom;
	@NotBlank
	private String nom;
	@NotBlank
	private String telephone;
	@NotBlank
	private String email;
	
	
	public Personne() {
		super();
	}


	public Personne(String prenom, String nom, String telephone, String email) {
		super();
		this.prenom = prenom;
		this.nom = nom;
		this.telephone = telephone;
		this.email = email;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	
	
	
	
	
	
	
	
	
	
}