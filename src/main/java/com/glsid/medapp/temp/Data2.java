package com.glsid.medapp.temp;

import java.time.LocalDate;
import java.time.LocalTime;


import com.glsid.medapp.entities.Consultation;

final public class Data2 {

    public static Consultation consult;
    
    static {
    	consult= new Consultation(new Long(1),LocalDate.of(2028, 3, 30),LocalTime.of(12,30),LocalTime.of(12,45),"ASPRO + POMADA SAFRA",null,null,null);
    }

    

}