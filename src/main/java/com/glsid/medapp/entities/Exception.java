package com.glsid.medapp.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Exception {

	@Id @GeneratedValue
	private Long id;
	private Date date;
	private String raison;
	
	@ManyToOne
	@JoinColumn(name = "ID_MEDCIN")
	private Medecin medecin;
	
	
	public Exception() {
		super();
	}


	public Exception(Date date, String raison) {
		super();
		this.date = date;
		this.raison = raison;
	}
	
	
	


	public Exception(Long id, Date date, String raison, Medecin medecin) {
		super();
		this.id = id;
		this.date = date;
		this.raison = raison;
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


	public String getRaison() {
		return raison;
	}


	public void setRaison(String raison) {
		this.raison = raison;
	}


	public Medecin getMedecin() {
		return medecin;
	}


	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}
	
	
	
	
	
	
	
}
