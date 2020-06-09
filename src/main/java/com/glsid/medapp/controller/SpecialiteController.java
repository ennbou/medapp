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
	
	@GetMapping(path = "/specialite")
	public String specialite(Model model,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="10")int size,
			@RequestParam(name="motCle",defaultValue="")String motCle
			) {
        Page<Specialite> pageSpecialites = specialiteRepository
        		.findByNom(motCle, PageRequest.of(page, size));
		model.addAttribute("pageSpecialites", pageSpecialites);
		int[] pages =  new int[pageSpecialites.getTotalPages()];
		model.addAttribute("pages",pages);
		model.addAttribute("size", size);
		model.addAttribute("motCle", motCle);
		model.addAttribute("currentPage",page);
		return "specialite/index";
	}
	@GetMapping(path = "/listeSpecialite")
	public String listSpecialite(Model model,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="10")int size
			) {
        Page<Specialite> pageSpecialites = specialiteRepository.findAll(PageRequest.of(page, size));
		model.addAttribute("pageSpecialites", pageSpecialites);
		int[] pages =  new int[pageSpecialites.getTotalPages()];
		model.addAttribute("pages",pages);
		model.addAttribute("size", size);
		model.addAttribute("currentPage",page);
		return "specialite/listeSpecialite";
	}
	
	@GetMapping(path = "/deleteSpecialites")
	public String delete(Long id, String motCle, String page, String size) {
		specialiteRepository.deleteById(id);
		return "redirect:/listeSpecialite?page="+page+"&size="+size;
	}
	
	@GetMapping(path = "/ajouterSpecialite")
	public String formSpecialite(Model model) {
		Specialite specialite = new Specialite();
		model.addAttribute("specialite", specialite);
		return "specialite/FormSpecialite";
	}
	
	@GetMapping(path = "/edit")
	public String edit(Model model, Long id) {
		Specialite specialite = specialiteRepository.getOne(id);
		model.addAttribute("specialite", specialite);
		return "specialite/EditSpecialite";
	}
	
	@PostMapping(path = "/save")
	public String save(Model model, @Valid Specialite specialite
			,BindingResult bindResult) {
		if(bindResult.hasErrors()) {
			return "specialite/FormSpecialite";
		}
		specialiteRepository.save(specialite);
		return "specialite/confirmation";
	}

	@RequestMapping("/specialite/{id}/medcins")
	public String listMedcins(@PathVariable() Long id, Model model){
        model.addAttribute("medcins", specialiteRepository.findById(id).get().getListMedecins());
		return "specialite/medcins";
	}
}
