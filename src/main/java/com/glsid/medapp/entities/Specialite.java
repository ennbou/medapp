package com.glsid.medapp.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Specialite {

	@Id @GeneratedValue
	private Long id;
	private String nom;
	@OneToMany(mappedBy = "specialite")
	private Collection<RendezVous> listRendezVous;
	
	@OneToMany(mappedBy = "specialite")
	private Collection<Medecin> listMedecins;
	
	
	
	public Specialite() {
		super();
	}



	public Specialite(String nom) {
		super();
		this.nom = nom;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public Collection<RendezVous> getListRendezVous() {
		return listRendezVous;
	}



	public void setListRendezVous(Collection<RendezVous> listRendezVous) {
		this.listRendezVous = listRendezVous;
	}



	public Collection<Medecin> getListMedecins() {
		return listMedecins;
	}



	public void setListMedecins(Collection<Medecin> listMedecins) {
		this.listMedecins = listMedecins;
	}
	
	
	
	
	
	
	
	
}
