package com.glsid.medapp.controller;

import javax.validation.Valid;

import com.glsid.medapp.dao.ConsultationRepository;
import com.glsid.medapp.dao.RendezVousRepository;
import com.glsid.medapp.modele.Consultation;
import com.glsid.medapp.modele.RendezVous;
import com.glsid.medapp.dao.DossierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.glsid.medapp.dao.PatientRepository;
import com.glsid.medapp.modele.Patient;
import com.glsid.medapp.service.IPatientService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    DossierRepository dossierRepository;

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
        Page<Patient> list = service.listPatient(motCle, page, size);
        //Page<Patient> list = patientRepository.findAll(PageRequest.of(page, size));
        int[] pages = new int[list.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("list", list);
        model.addAttribute("size", size);
        model.addAttribute("currentPage", page);
        model.addAttribute("motCle", motCle);
        return "/patient/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Patient p = new Patient();
        model.addAttribute("patient", p);
        return "patient/create";
    }

    @PostMapping("/save")
    public String save(@Valid Patient patient,@RequestParam("imageFile") MultipartFile imageFile, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "/patient/create";


            String pathImage = imageFile.getOriginalFilename();
            if(!pathImage.isEmpty()){
                patient.setImage(pathImage);
                try {
                    service.saveImage(imageFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        patientRepository.save(patient);

        return "redirect:/patient/index?motCle=";
    }


    @RequestMapping("/{id}/edit")
    public String edit(@PathVariable long id, Model model) {

        Patient patient = patientRepository.findById(id).get();
        model.addAttribute("patient", patient);
        return "patient/edit";
    }

    @RequestMapping("/{id}/update")
    public String update(Patient patient,@RequestParam("imageFile") MultipartFile imageFile, @PathVariable Long id) {
        Patient p = patientRepository.findById(id).get();
        String pathImage = imageFile.getOriginalFilename();
        if(!pathImage.isEmpty()){
            p.setImage(pathImage);
            p = patient;
            try {
                service.saveImage(imageFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        patientRepository.save(p);

        return "redirect:/patient/index?motCle=";
    }

    @RequestMapping("/{id}/delete")
    public String delete(@PathVariable long id) {
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

    @RequestMapping("/{id}/rdvs")
    public String rdvs(@PathVariable Long id, Model model){
        model.addAttribute("rdvs", patientRepository.findById(id).get().getDossier().getListRendezVous());
        model.addAttribute("patient", patientRepository.findById(id).get());
        return "patient/rdvs";
    }

}
