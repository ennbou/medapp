package com.glsid.medapp.controller;

import javax.validation.Valid;

import com.glsid.medapp.dao.ConsultationRepository;
import com.glsid.medapp.dao.RendezVousRepository;
import com.glsid.medapp.modele.Consultation;
import com.glsid.medapp.modele.RendezVous;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.glsid.medapp.dao.PatientRepository;
import com.glsid.medapp.modele.Patient;
import com.glsid.medapp.service.IPatientService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO complete le formulaire d'ajouter(image, validation ... )
 * TODO button redection to create new Patient
 * TODO Recherche par CIN ou Nom&Prenom
 * TODO add page infos patient (RDV, Consultation)
 * TODO redirection pour ajouter un nouveau RDV
 */

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    RendezVousRepository rendezVousRepository;

    @Autowired
    ConsultationRepository consultationRepository;

    @Autowired
    IPatientService service;


    @RequestMapping(path = {"/liste", "/index", "/"})
    public String index(Model model, String motCle,
                        @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "size", defaultValue = "10") int size) {
//        Page<Patient> list = service.listPatient(motCle, page, 8);
        Page<Patient> list = patientRepository.findAll(PageRequest.of(page, size));
        int[] pages = new int[list.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("list", list);
        model.addAttribute("motCle", motCle);
        return "/patient/index";
    }

    @GetMapping("/create")
    public String create(Model model) {

        Patient p = Patient.builder().nom("nom").prenom("prenom").
                sexe(true).telephone("0666").email("email@email.com").address("address 474").
                dateNaissance(LocalDate.of(2020, 5, 1)).image("path").build();

        model.addAttribute("patient", p);
        return "patient/create";
    }

    @PostMapping("/save")
    public String save(@Valid Patient patient, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "/patient/create";
        patientRepository.save(patient);
        return "redirect:/patient/liste";
    }


    @RequestMapping("/edit")
    public String edit(long id, Model model) {

        Patient patient = patientRepository.findById(id).get();
        model.addAttribute("patient", patient);
        return "patient/edit";
    }

    @RequestMapping("/update")
    public String update(Patient patient, boolean sexe, Model model) {
        Patient p = patientRepository.findById(patient.getId()).get();
        p = patient;
        patientRepository.save(p);
        return "redirect:/patient/index?motCle=";
    }

    @RequestMapping("/delete")
    public String delete(long id) {
        patientRepository.deleteById(id);
        return "redirect:/patient/index?motCle=";
    }


    @RequestMapping("/detail/{id}")
    public String detail(Model model, @PathVariable(name = "id") long id) {
        List<RendezVous> rdvs = patientRepository.findById(id).get().getDossier().getListRendezVous();
        model.addAttribute("rdvs", rdvs);
        List<Consultation> consultations = new ArrayList<>();
        rdvs.forEach(rdv -> {
            Consultation c = rdv.getConsultation();
            if (c == null) return;
            consultations.add(c);
        });
        model.addAttribute("consultations", consultations);
        return "patient/detail";
    }

}
