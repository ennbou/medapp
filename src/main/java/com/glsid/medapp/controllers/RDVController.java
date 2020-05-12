package com.glsid.medapp.controllers;

import com.glsid.medapp.dao.RendezVousRepository;
import com.glsid.medapp.entities.Patient;
import com.glsid.medapp.entities.RendezVous;
import com.glsid.medapp.temp.Data;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rdv")
public class RDVController {
	
@Autowired
private RendezVousRepository rendezVousRepository;

    @RequestMapping(path = {"/", "/list"})
    public String liste(Model model) {
        model.addAttribute("rdvs", Data.listeRDV);
        return "rdv/list";
    }

    @RequestMapping()
    public String defaultt() {
        return "redirect:/rdv/list";
    }
    
    @GetMapping("/create")
    public String create(Model model) {
        RendezVous rv = new RendezVous();
        model.addAttribute("rendezVous", rv);
        return "rdv/create";
    }
    
    @PostMapping("/save")
    public String save(@Valid RendezVous rv, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "rdv/create";
        rendezVousRepository.save(rv);
        return "redirect:/rdv/list";
    }

}
