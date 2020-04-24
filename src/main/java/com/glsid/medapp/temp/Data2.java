package com.glsid.medapp.temp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.glsid.medapp.entities.Consultation;

final public class Data2 {

    public static List<Consultation> listeConsult = new ArrayList<>();
    
    static {
    	listeConsult.add(new Consultation(new Long(1),LocalDate.of(2028, 3, 30),LocalTime.of(12,30),LocalTime.of(12,45),"ASPRO + POMADA SAFRA"));
    }
    
    

}