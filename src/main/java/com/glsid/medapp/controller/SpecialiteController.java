package com.glsid.medapp.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.glsid.medapp.dao.SpecialiteRepository;
import com.glsid.medapp.modele.Specialite;

@Controller
public class SpecialiteController {
	
	@Autowired
	public SpecialiteRepository specialiteRepository;
	
	@GetMapping(path = "/specialite/listeSpecialite")
	public String listSpecialite(Model model,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="10")int size,
			@RequestParam(name="motCle",defaultValue="")String motCle
			) {
        Page<Specialite> pageSpecialites = specialiteRepository
        		.listSpecialite(motCle, PageRequest.of(page, size));
		model.addAttribute("pageSpecialites", pageSpecialites);
		int[] pages =  new int[pageSpecialites.getTotalPages()];
		model.addAttribute("pages",pages);
		model.addAttribute("size", size);
		model.addAttribute("motCle", motCle);
		model.addAttribute("currentPage",page);
		return "specialite/listeSpecialite";
	}
	
	@GetMapping(path = "/specialite/deleteSpecialites")
	public String delete(Long id, String page, String size) {
		if(!specialiteRepository.findById(id).get().getListMedecins().isEmpty()) {
			return "specialite/alert";
		}
		specialiteRepository.deleteById(id);
		return "redirect:/specialite/listeSpecialite?page="+page+"&size="+size;
	}
	
	@GetMapping(path = "/specialite/ajouterSpecialite")
	public String formSpecialite(Model model) {
		Specialite specialite = new Specialite();
		model.addAttribute("specialite", specialite);
		return "specialite/FormSpecialite";
	}
	
	@GetMapping(path = "/specialite/edit")
	public String edit(Model model, Long id) {
		Specialite specialite = specialiteRepository.getOne(id);
		model.addAttribute("specialite", specialite);
		return "specialite/EditSpecialite";
	}
	
	@PostMapping(path = "/specialite/save")
	public String save(Model model, @Valid Specialite specialite, BindingResult bindResult) {
		if(bindResult.hasErrors()) {
			return "specialite/FormSpecialite";
		}
		specialiteRepository.save(specialite);
		return "redirect:/specialite/listeSpecialite";
	}

	@RequestMapping("/specialite/{id}/medecins")
	public String listMedecins(@PathVariable() Long id, Model model){
        model.addAttribute("medecins", specialiteRepository.findById(id).get().getListMedecins());
        model.addAttribute("specialite", specialiteRepository.findById(id).get());
		return "specialite/medecins";
	}
}
