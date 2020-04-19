package com.glsid.medapp.enteties;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Medecin extends Personne{
	
	
	
	@OneToMany(mappedBy = "medecin", fetch = FetchType.LAZY)
	private Collection<RendezVous> listRendezVous;

	public Medecin() {
		super();
	}

	public Medecin(String prenom, String nom, String telephone, String email) {
		super(prenom, nom, telephone, email);
	}

	public Collection<RendezVous> getListRendezVous() {
		return listRendezVous;
	}

	public void setListRendezVous(Collection<RendezVous> listRendezVous) {
		this.listRendezVous = listRendezVous;
	}
	
	
	

}
