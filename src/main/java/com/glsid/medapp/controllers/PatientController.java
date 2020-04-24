package com.glsid.medapp.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.glsid.medapp.dao.PatientRepository;
import com.glsid.medapp.entities.Patient;
import com.glsid.medapp.PatientService.IPatientService;

@Controller
public class PatientController {
	
	
	@Autowired
	PatientRepository patientRepository;
	@Autowired
	IPatientService service;

	@RequestMapping("/patient/index")
	public String index(Model model, String motCle, 
			@RequestParam(name = "page", defaultValue = "0")int page) {
		Page<Patient> list = service.listPatient(motCle, page, 8);
		int[] pages = new int[list.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("list", list);
		model.addAttribute("motCle", motCle);
		return "patient/index";
	}
	
	@RequestMapping("/patient/create")
	public String create(Model model) {
		model.addAttribute("patient", new Patient());
		return "patient/create";
	}
	
	@PostMapping("/patient/save")
	public String save(@Valid Patient patient, BindingResult bindingResult, boolean sexe, Model model) {
		if(bindingResult.hasErrors())
		return "patient/create";
		patient.setSexe(sexe);
		patientRepository.save(patient);
		return "redirect:/patient/edit?id="+patient.getId();
	}
	
	
	@RequestMapping("/patient/edit")
	public String edit(long id, Model model) {
		
		Patient patient = patientRepository.findById(id).get();
		model.addAttribute("patient", patient);
		return "patient/edit";
	}
	
	@RequestMapping("/patient/update")
	public String update(Patient patient, boolean sexe, Model model) {
		Patient p = patientRepository.findById(patient.getId()).get();
		p = patient;
		patientRepository.save(p);
		return "redirect:/patient/index?motCle=";
	}
	
	@RequestMapping("/patient/delete")
	public String delete(long id) {
		patientRepository.deleteById(id);
		return "redirect:/patient/index?motCle=";
	}
	
	
	
}
