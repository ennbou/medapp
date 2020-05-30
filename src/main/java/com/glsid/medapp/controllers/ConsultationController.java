package com.glsid.medapp.controllers;
import java.util.List;

import com.glsid.medapp.dao.ConsultationRepository;
import com.glsid.medapp.entities.Consultation;
import com.glsid.medapp.temp.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/consult")
public class ConsultationController {
	
	@Autowired
	private ConsultationRepository consultationController;
	
	@GetMapping({"/",""})
    public String All(Model model) {
		List<Consultation> consults = consultationController.findAll();
		// just example 
		consults.add(Data2.consult);
        model.addAttribute("consults", consults);
        return "consult/list";
    }
	
	@GetMapping("/{id}")
    public String one(Model model,@PathVariable Long id) {
		// not implemented yet
        model.addAttribute("consult",Data2.consult);
        return "consult/consultById";
    }
	
	@PostMapping("/{id}")
	public String save(@RequestBody Consultation consult,@PathVariable Long id) {
		consult.setId(id);
		consultationController.save(consult);
        return "consult/list";
    }
	
	// the same thing
	@PutMapping("/{id}")
	public String update(@RequestBody Consultation consultation,@PathVariable Long id) {
		consultation.setId(id);
		consultationController.save(consultation);
		return "consult/list";
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id) {
		consultationController.deleteById(id);
		return "consult/list";
	}
	
}
