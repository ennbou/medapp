package com.glsid.medapp.controllers;
import java.util.List;
import com.glsid.medapp.entities.Consultation;
import com.glsid.medapp.temp.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/consult")
public class ConsultationController {
	
	@RequestMapping(path ="/{id}")
    public String liste(Model model,@PathVariable int id) {
		List<Consultation> data=Data2.listeConsult;
        model.addAttribute("consult", data.get(id));
        return "consult/consultById";
    }
	
	@RequestMapping(path ="/")
    public String liste(Model model) {
		List<Consultation> data=Data2.listeConsult;
        model.addAttribute("consult", data);
        return "consult/list";
    }

}
