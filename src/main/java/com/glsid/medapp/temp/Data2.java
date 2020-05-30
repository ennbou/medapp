package com.glsid.medapp.temp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.glsid.medapp.modele.Consultation;

final public class Data2 {

    public static Consultation consult;
    
    static {
    	DateFormat timeFormat=new SimpleDateFormat("HH:mm");
    	DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
    	try {
    	consult= new Consultation(null,dateFormat.parse("2020-03-15"),timeFormat.parse("12:30"),timeFormat.parse("12:45"),"ASPRO + POMADA SAFRA",null,null,null);
    	}catch(Exception e) {}
    	}

    

}