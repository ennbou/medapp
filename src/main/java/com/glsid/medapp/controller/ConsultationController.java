package com.glsid.medapp.controller;

import com.glsid.medapp.dao.ConsultationRepository;
import com.glsid.medapp.modele.Consultation;

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

@Controller
@RequestMapping("/consult")
public class ConsultationController {
	
	@Autowired
	private ConsultationRepository consultationRepository;
	
	// get all consultations
	@GetMapping({"","/","/liste"})
    public String All(Model model,@RequestParam(name="search",defaultValue="") String search,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
		//Page<Consultation> consults = consultationRepository.findAll(PageRequest.of(page, size));
		Page<Consultation> consults = consultationRepository.searchUsingWord(search, PageRequest.of(page, size));
        int[] pages = new int[consults.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("size", size);
        model.addAttribute("consults", consults);
        model.addAttribute("pageActual", page);
        model.addAttribute("search", search);
        return "consult/listeConsult";
    }
	
	// get/update consultation by id
	@GetMapping("/{id}")
    public String one(Model model,@PathVariable Long id) {
		Consultation consult = consultationRepository.findById(id).get();
        model.addAttribute("consult",consult);
        return "consult/consultPatient";
    }
	
	// update consultation
	@GetMapping("/modification")
	public String update(Long id) {
		return "redirect:"+id;
	}
	
	// save consultation
	@PostMapping("/save")
	public String save(@Valid Consultation consultation, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "consult/consultPatient";
		}
		consultationRepository.save(consultation);
		return "redirect:"+"";
	}
	
	// delete consultation
	@GetMapping("/suppression")
	public String delete(Long id, String page, String size, String search) {
		consultationRepository.deleteById(id);
		return "redirect:"+"?page="+page+"&size="+size+"&search="+search;
	}
	
	// cas d'erreur
	@GetMapping(path = "/403")
	public String accessDenied() {
		return "403";
	}
	
}
