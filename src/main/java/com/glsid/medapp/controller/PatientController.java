package com.glsid.medapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.glsid.medapp.MedappApplication;
import com.glsid.medapp.config.MyUserDetails;
import com.glsid.medapp.config.ROLE;
import com.glsid.medapp.dao.ConsultationRepository;
import com.glsid.medapp.dao.RendezVousRepository;
import com.glsid.medapp.dao.SpecialiteRepository;
import com.glsid.medapp.modele.Consultation;
import com.glsid.medapp.modele.RendezVous;
import com.glsid.medapp.dao.DossierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.glsid.medapp.dao.PatientRepository;
import com.glsid.medapp.modele.Patient;
import com.glsid.medapp.service.IPatientService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientController {

	private PasswordEncoder encoder=MedappApplication.getEncoder();
	
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DossierRepository dossierRepository;
    @Autowired
    RendezVousRepository rendezVousRepository;
    @Autowired
    ConsultationRepository consultationRepository;
    @Autowired
    SpecialiteRepository specialiteRepository;
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
        //model.addAttribute("dateNow",LocalDate.now());
        return "patient/create";
    }

    @PostMapping("/save")
    public String save(@Valid Patient patient,@RequestParam("password") String pass, @RequestParam("imageFile") MultipartFile imageFile, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "/patient/create";


        String pathImage = imageFile.getOriginalFilename();
        if (!pathImage.isEmpty()) {
            patient.setImage(pathImage);
            try {
                service.saveImage(imageFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        patient.setPassword(encoder.encode(pass));
        patient.setRoles(ROLE.ROLE_PATIENT.name());
        //problem with image
        patient.setImage("path");
        //
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
    public String update(@Valid Patient patient,@RequestParam("passwordPatient") String password, 
    		@RequestParam("imageFile") MultipartFile imageFile, @PathVariable Long id) {
        //Patient p = patientRepository.findById(id).get();
    	
    	if(!password.contentEquals("")) {
    		patient.setPassword(encoder.encode(password));
    	}
    	
        String pathImage = imageFile.getOriginalFilename();
        if (!pathImage.isEmpty()) {
            patient.setImage(pathImage);
            // p = patient;
            try {
                service.saveImage(imageFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        patientRepository.save(patient);

        return "redirect:/patient/detail/"+patient.getId();
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
        
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof MyUserDetails && ((MyUserDetails) principal).getAuthorities().contains(new SimpleGrantedAuthority("ROLE_PATIENT"))) {
        	if(id!=((MyUserDetails) principal).getId()) {
        		return "redirect:/patient/detail/"+((MyUserDetails) principal).getId();
        	}
        }
        	
        
        rdvs.forEach(rdv -> {
            Consultation c = rdv.getConsultation();
            if (c == null) return;
            consultations.add(c);
        });
        model.addAttribute("consultations", consultations);
        model.addAttribute("patient",patientRepository.findById(id).get());
        model.addAttribute("spes",specialiteRepository.findAll());
        return "patient/detail";
    }

    @RequestMapping("/detail")
    public String detail(HttpServletRequest request) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id;
        if (principal instanceof MyUserDetails)
            id = ((MyUserDetails) principal).getId();
        else
            id = 1;

        return "redirect:/patient/detail/" + id;

    }
    
    @PostMapping("/search")
    public String search(@RequestParam("cin") String cin) {
    	Patient p=patientRepository.search(cin);
    	if(p!=null)
    		return "redirect:/patient/detail/" + p.getId();
    	return "redirect:/patient/index?motCle=";
    }
    
    @PostMapping("/detail/save")
    public String save(HttpServletRequest request,@RequestParam  Long idpatient) {
    	RendezVous rv=new RendezVous();
    	rv.setDescription(request.getParameter("description").trim());
    	rv.setSpecialite(specialiteRepository.findById(Long.valueOf(request.getParameter("idspec").trim())).get());
    	rv.setDate(LocalDateTime.now());
    	rv.setDossier(patientRepository.findById(idpatient).get().getDossier());
        rendezVousRepository.save(rv);
        
        return "redirect:/patient/detail/" + idpatient;
    }



    @RequestMapping("/{id}/rdvs")
    public String rdvs(@PathVariable Long id, Model model) {
        model.addAttribute("rdvs", patientRepository.findById(id).get().getDossier().getListRendezVous());
        model.addAttribute("patient", patientRepository.findById(id).get());
        return "patient/rdvs";
    }

}
