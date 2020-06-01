package com.glsid.medapp.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.glsid.medapp.dao.PatientRepository;
import com.glsid.medapp.entities.Patient;
import com.glsid.medapp.PatientService.IPatientService;

import java.time.LocalDate;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    PatientRepository patientRepository;

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


    @RequestMapping("/test")
    public String test() {
        Patient p1 = Patient.builder().nom("nom1").prenom("prenom1").
                sexe(true).telephone("0611").email("email@email").address("adress 474").
                dateNaissance(LocalDate.of(2020, 10, 1)).image("path").build();
        patientRepository.save(p1);
        return "rdv/liste";
    }

}
