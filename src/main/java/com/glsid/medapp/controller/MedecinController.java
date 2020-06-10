package com.glsid.medapp.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.glsid.medapp.dao.MedecinRepository;
import com.glsid.medapp.dao.SpecialiteRepository;
import com.glsid.medapp.modele.Medecin;

@Controller
public class MedecinController {
	
	@Autowired
	public MedecinRepository medecinRepository;
	@Autowired
	public SpecialiteRepository specialiteRepository;
	
	@GetMapping(path = "/medecin/search")
	public String medecin(Model model,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="10")int size,
			@RequestParam(name="motCle",defaultValue="")String motCle
			) {
        Page<Medecin> pageMedecins = medecinRepository
        		.findByNom(motCle, PageRequest.of(page, size));
		model.addAttribute("pageMedecins", pageMedecins);
		int[] pages =  new int[pageMedecins.getTotalPages()];
		model.addAttribute("pages",pages);
		model.addAttribute("size", size);
		model.addAttribute("motCle", motCle);
		model.addAttribute("currentPage",page);
		return "medecin/index";
	}
	
	@GetMapping(path = "/medecin/listeMedecin")
	public String listSpecialite(Model model,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="10")int size
			) {
        Page<Medecin> pageMedecins = medecinRepository.findAll(PageRequest.of(page, size));
		model.addAttribute("pageMedecins", pageMedecins);
		int[] pages =  new int[pageMedecins.getTotalPages()];
		model.addAttribute("pages",pages);
		model.addAttribute("size", size);
		model.addAttribute("currentPage",page);
		return "medecin/listeMedecin";
	}
	
	@GetMapping(path = "/medecins/deleteMedecins")
	public String delete(Long id, String page, String size) {
		medecinRepository.deleteById(id);
		return "redirect:/medecin/listeMedecin?page="+page+"&size="+size;
	}
	
	@GetMapping(path = "/medecin/ajouterMedecin")
	public String formMedecin(Model model) {
		Medecin medecin = new Medecin();
		model.addAttribute("medecin", medecin);
		return "medecin/FormMedecin";
	}
	
	@GetMapping(path = "/medecin/editMedecin")
	public String edit(Model model, Long id) {
		Medecin medecin = medecinRepository.getOne(id);
		model.addAttribute("medecin",medecin);
		return "medecin/EditMedecin";
	}
	
	@PostMapping(path = "/medecin/saveMedecin")
	public String save(Model model,@Valid Medecin medecin, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "medecin/FormMedecin";
		}
		medecinRepository.save(medecin);
		return "medecin/confirmation";
	}
	
	@RequestMapping("/medecin/{id}/consultations")
	public String listConsultations(@PathVariable Long id, Model model) {
		model.addAttribute("consultations", medecinRepository.findById(id).get().getListConsultations());
		return "medecin/consultations";
	}
}