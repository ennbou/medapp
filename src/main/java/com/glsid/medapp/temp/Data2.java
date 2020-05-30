package com.glsid.medapp.temp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.glsid.medapp.modele.Consultation;
import com.glsid.medapp.modele.Dossier;
import com.glsid.medapp.modele.Medecin;
import com.glsid.medapp.modele.RendezVous;

final public class Data2 {

    public static Consultation consult;
    public static Medecin medecin=null;
    public static Dossier dossier=null;
    public static RendezVous rendezVous=null;
    
    static {
    	DateFormat timeFormat=new SimpleDateFormat("HH:mm");
    	DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
    	try {
    
    		//medecin =new Medecin();
    		//dossier=new Dossier();
    		//rendezVous=new RendezVous();
    		
    		consult= new Consultation(null,dateFormat.parse("2020-03-15"),timeFormat.parse("12:30"),
    				timeFormat.parse("12:45"),"ASPRO + POMADA SAFRA",rendezVous,medecin,dossier);
    	}catch(Exception e) {}
    	}

    

}