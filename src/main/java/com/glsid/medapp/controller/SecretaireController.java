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

import com.glsid.medapp.dao.SecretaireRepository;
import com.glsid.medapp.modele.Secretaire;


@Controller
public class SecretaireController {
	
	@Autowired
	SecretaireRepository secretaireRepository;
	
	@GetMapping(path = "/secretaire/search")
	public String secreBtaire(Model model,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="10")int size,
			@RequestParam(name="motCle",defaultValue="")String motCle
			) {
        Page<Secretaire> pageSecretaires = secretaireRepository
        		.findByNom(motCle, PageRequest.of(page, size));
		model.addAttribute("pageSecretaires", pageSecretaires);
		int[] pages =  new int[pageSecretaires.getTotalPages()];
		model.addAttribute("pages",pages);
		model.addAttribute("size", size);
		model.addAttribute("motCle", motCle);
		model.addAttribute("currentPage",page);
		return "secretaire/index";
	}

	@GetMapping(path = "/secretaire/listeSecretaire")
	public String listSecretaire(Model model,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="10")int size
			) {
        Page<Secretaire> pageSecretaires = secretaireRepository.findAll(PageRequest.of(page, size));
		model.addAttribute("pageSecretaires", pageSecretaires);
		int[] pages =  new int[pageSecretaires.getTotalPages()];
		model.addAttribute("pages",pages);
		model.addAttribute("size", size);
		model.addAttribute("currentPage",page);
		return "secretaire/listeSecretaire";
	}
	
	@GetMapping(path = "/secretaire/deleteSecretaires")
	public String delete(Long id, String page, String size) {
		secretaireRepository.deleteById(id);
		return "redirect:/secretaire/listeSecretaire?page="+page+"&size="+size;
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
		secretaireRepository.save(secretaire);
		return "secretaire/confirmation";
	}
	
	@RequestMapping("/secretaire/{id}/rendezVous")
	public String listRendezVous(@PathVariable Long id, Model model) {
		model.addAttribute("rendezVous", secretaireRepository.findById(id).get().getListRendezVous());
		return "secretaire/rendezVous";
	}
}