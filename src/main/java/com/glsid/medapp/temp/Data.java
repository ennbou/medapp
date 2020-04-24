package com.glsid.medapp.temp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

final public class Data {

    public static List<RDV> listeRDV = new ArrayList<>();

    //DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")

    static {
        for (int i = 0; i < 27; i++) {
            listeRDV.add(new RDV((long) 1,
                    LocalDateTime.of(2020, 2, i + 1, 10, 30),
                    (i % 4 == 0) ? LocalDate.of(2020, 3, i + 1) : null,
                    "Patient " + i + 10, "Spescialite " + ((i % 2 == 0) ? "1" : "2"), (i % 4 == 0) ? "medecine " + ((i % 4 == 0) ? "1" : "2") : "",
                    "description pour le maladie x parce que " + i + 20, i % 4 == 0));

        }
    }

}
