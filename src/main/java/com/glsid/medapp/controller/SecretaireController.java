package com.glsid.medapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.glsid.medapp.dao.SecretaireRepository;
import com.glsid.medapp.modele.Secretaire;

@Controller
@RequestMapping("/secretaire")
public class SecretaireController {
	
	
	@Autowired
	SecretaireRepository secretaireRepository;
	
	
	
	@RequestMapping("/index")
	public String index(Model model, @RequestParam(name = "page", defaultValue = "0")int page) {
		Page<Secretaire> list = secretaireRepository.findAll(PageRequest.of(page, 8));
		int[] pages = new int[list.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("list", list);
		return "secretaire/index";
	}

	
	@RequestMapping("/create")
	public String create(Model model) {
		model.addAttribute("secretaire", new Secretaire());
		return "secretaire/create";
	}
	
	
	@PostMapping("/save")
	public String save(@Valid Secretaire secretaire, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors())
		return "secretaire/create";
		secretaireRepository.save(secretaire);
		return "redirect:/secretaire/edit?id="+secretaire.getId();
	}
	
	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable long id, Model model) {
		
		Secretaire secretaire = secretaireRepository.findById(id).get();
		model.addAttribute("secretaire", secretaire);
		return "secretaire/edit";
	}
	
	
	@RequestMapping("/update")
	public String update(Secretaire secretaire, Model model) {
		Secretaire s = secretaireRepository.findById(secretaire.getId()).get();
		s = secretaire;
		secretaireRepository.save(s);
		return "redirect:/secretaire/index";
	}
	@RequestMapping("/{id}/delete")
	public String delete(@PathVariable long id) {
		secretaireRepository.deleteById(id);
		return "redirect:/secretaire/index";
	}	
}
