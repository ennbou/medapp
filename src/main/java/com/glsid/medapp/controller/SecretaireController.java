package com.glsid.medapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.glsid.medapp.MedappApplication;
import com.glsid.medapp.dao.SecretaireRepository;
import com.glsid.medapp.modele.Secretaire;


@Controller
public class SecretaireController {
	
	private PasswordEncoder encoder=MedappApplication.getEncoder();
	
	@Autowired
	SecretaireRepository secretaireRepository;
	
	@GetMapping(path = "/secretaire/listeSecretaire")
	public String listSecretaire(Model model,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="10")int size,
			@RequestParam(name="motCle",defaultValue="")String motCle
			) {
        Page<Secretaire> pageSecretaires = secretaireRepository
        		.listSecretaire(motCle, PageRequest.of(page, size));
		model.addAttribute("pageSecretaires", pageSecretaires);
		int[] pages =  new int[pageSecretaires.getTotalPages()];
		model.addAttribute("pages",pages);
		model.addAttribute("size", size);
		model.addAttribute("motCle", motCle);
		model.addAttribute("currentPage",page);
		return "secretaire/listeSecretaire";
	}
	
	@GetMapping(path = "/secretaire/ajouterSecretaire")
	public String formSecretaire(Model model) {
		Secretaire secretaire = new Secretaire();
		model.addAttribute("secretaire", secretaire);
		return "secretaire/FormSecretaire";
	}
	
	@GetMapping(path = "/secretaire/editSecretaire")
	public String edit(Model model, Long id) {
		Secretaire secretaire = secretaireRepository.getOne(id);
		model.addAttribute("secretaire",secretaire);
		return "secretaire/EditSecretaire";
	}
	
	@PostMapping(path = "/secretaire/saveSecretaire")
	public String save(Model model,@Valid Secretaire secretaire, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "secretaire/FormSecretaire";
		}
		secretaire.setPassword(encoder.encode(secretaire.getPassword()));
		secretaireRepository.save(secretaire);
		return "redirect:/secretaire/listeSecretaire";
	}
	
}