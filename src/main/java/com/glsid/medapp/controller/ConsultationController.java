package com.glsid.medapp.controller;

import com.glsid.medapp.dao.ConsultationRepository;
import com.glsid.medapp.dao.MedecinRepository;
import com.glsid.medapp.modele.Consultation;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
	@Autowired
	private MedecinRepository medecinRepository;
	
	// get all consultations
	@GetMapping({"","/","/liste"})
    public String All(Model model, @RequestParam(name="search",defaultValue="") String search, LocalDate d1, LocalDate d2,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
		//Page<Consultation> consults = consultationRepository.findAll(PageRequest.of(page, size));
		d1= LocalDate.of(2000, 1, 1);
		d2= LocalDate.now();
		Page<Consultation> consults = consultationRepository.searchUsingDate(search, d1, d2, PageRequest.of(page, size));
        int[] pages = new int[consults.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("size", size);
        model.addAttribute("consults", consults);
        model.addAttribute("pageActual", page);
        model.addAttribute("search", search);
	    model.addAttribute("date1", d1);
	    model.addAttribute("date2", d2);
        return "consult/listeConsult";
    }
	
	// get/update consultation by id
	@GetMapping("/{id}")
    public String one(Model model,@PathVariable Long id) {
		Consultation consult = consultationRepository.findById(id).get();
        model.addAttribute("consult",consult);
        model.addAttribute("medecins",medecinRepository.findAll());
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
	public String delete(Long id, String page, String size) {
		consultationRepository.deleteById(id);
		return "redirect:"+"?page="+page+"&size="+size;
	}
	
	// get consultations by date and search
	@PostMapping("/recherche")
	public String searchDateAvancee(Model model, @RequestParam(name = "search",defaultValue="") String search,
			@RequestParam(name = "date1") String d1, 
			@RequestParam(name = "date2") String d2,
	        @RequestParam(name = "page", defaultValue = "0") int page,
	        @RequestParam(name = "size", defaultValue = "10") int size) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date1 = LocalDate.parse(d1, formatter);
		LocalDate date2 = LocalDate.parse(d2, formatter);
		Page<Consultation> consults = consultationRepository.searchUsingDate(search, date1, date2, PageRequest.of(page, size));
	    int[] pages = new int[consults.getTotalPages()];
	    model.addAttribute("pages", pages);
	    model.addAttribute("size", size);
	    model.addAttribute("consults", consults);
	    model.addAttribute("pageActual", page);
	    model.addAttribute("search", search);
	    model.addAttribute("date1", d1);
	    model.addAttribute("date2", d2);
	    return "consult/listeConsult";
	}
	
	// cas d'erreur
	@GetMapping("/403")
	public String accessDenied() {
		return "403";
	}
}
