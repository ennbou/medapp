package com.glsid.medapp.controllers;

import com.glsid.medapp.temp.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rdv")
public class RDVController {

    @RequestMapping(path = {"/", "/liste"})
    public String liste(Model model) {
        model.addAttribute("rdvs", Data.listeRDV);
        return "/rdv/liste";
    }

    @RequestMapping()
    public String defaultt() {
        return "redirect:/rdv/liste";
    }

}
