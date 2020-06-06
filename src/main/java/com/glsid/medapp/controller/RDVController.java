package com.glsid.medapp.controller;

import com.glsid.medapp.dao.PatientRepository;
import com.glsid.medapp.dao.RendezVousRepository;
import com.glsid.medapp.dao.SpecialiteRepository;
import com.glsid.medapp.modele.RendezVous;
import com.glsid.medapp.temp.Data;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rdv")
public class RDVController {
	
@Autowired
private RendezVousRepository rendezVousRepository;
@Autowired
private PatientRepository patientRepository;
@Autowired
SpecialiteRepository specialiteRepository;

    @RequestMapping(path = {"/", "/list"})
    public String liste(Model model) {
        model.addAttribute("rdvs", Data.listeRDV);
        return "rdv/list";
    }

    @RequestMapping()
    public String defaultt() {
        return "redirect:/rdv/list";
    }
    
    @GetMapping("/{id}/create")
    public String create(@PathVariable Long id, Model model) {
        RendezVous rv = new RendezVous();
        model.addAttribute("rendezVous", rv);
        model.addAttribute("patient", patientRepository.findById(id).get());
        model.addAttribute("spes",specialiteRepository.findAll());
        return "rdv/create";
    }
    
    @PostMapping("/{id}/save")
    public String save(@Valid RendezVous rv, @RequestParam Long idpatient, @RequestParam Long idspec, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "rdv/create";
        rv.setDossier(patientRepository.findById(idpatient).get().getDossier());
        rv.setSpecialite(specialiteRepository.findById(idspec).get());
        rendezVousRepository.save(rv);
        return "redirect:/rdv/list";
    }

}
