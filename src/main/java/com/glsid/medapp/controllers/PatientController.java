package com.glsid.medapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PatientController {

    @GetMapping("/")
    public String index() {
        return "fr_template";
    }

}
