package com.glsid.medapp.controller;

import com.glsid.medapp.dao.PatientRepository;
import com.glsid.medapp.dao.RendezVousRepository;
import com.glsid.medapp.dao.SpecialiteRepository;
import com.glsid.medapp.modele.RendezVous;

import javax.validation.Valid;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rdv")
public class RDVController {
	
@Autowired
private RendezVousRepository rendezVousRepository;
@Autowired
private PatientRepository patientRepository;
@Autowired
SpecialiteRepository specialiteRepository;

    @PostMapping("/search")
    public String search(Model model, SearchFrom searchFrom) {

        List<RendezVous> list = new ArrayList<>();
        if (searchFrom != null) {
            List<RendezVous> liste;
            if (searchFrom.specialites.length == 0)
                liste = rendezVousRepository.findAllAdvencedSearch(searchFrom.nom, searchFrom.prenom);
            else
                liste = rendezVousRepository.findAllAdvencedSearch(searchFrom.nom, searchFrom.prenom, Arrays.asList(searchFrom.specialites));
            liste.forEach(item -> {
                LocalDateTime dt, dt1, dt2;
                LocalDate dtC, dtC1, dtC2;
                dt1 = dt2 = null;
                dtC1 = dtC2 = null;

                Boolean c = false;
                Boolean r = false;

                dt = item.getDate();
                dtC = item.getConsultation().getDate();

                if (!searchFrom.dateCreation1.isEmpty())
                    dt1 = LocalDateTime.parse(searchFrom.dateCreation1);
                if (!searchFrom.dateCreation2.isEmpty())
                    dt2 = LocalDateTime.parse(searchFrom.dateCreation2);
                if (!searchFrom.dateRdv1.isEmpty())
                    dtC1 = LocalDate.parse(searchFrom.dateRdv1);
                if (!searchFrom.dateRdv2.isEmpty())
                    dtC2 = LocalDate.parse(searchFrom.dateRdv2);


                if ((dt1 != null && (dt.isEqual(dt1) || dt.isAfter(dt1))) || (dt2 != null && (dt.isEqual(dt2) || dt.isBefore(dt2)))) {
                    c = true;
                }

                if ((dtC1 != null && (dtC.isEqual(dtC1) || dtC.isAfter(dtC1))) || (dtC2 != null && (dtC.isEqual(dtC2) || dtC.isBefore(dtC2)))) {
                    r = true;
                }

                if ((c || (dt1 == null && dt2 == null)) && (r || (dtC1 == null && dtC2 == null))) {
                    list.add(item);
                }

            });
        }
//        Page<RendezVous> liste = new PageImpl(list);

        model.addAttribute("rdvs", list);
        model.addAttribute("formSearch", searchFrom);
        model.addAttribute("specialites", specialiteRepository.findAll());

        return "rdv/list";
    }

    @RequestMapping(path = {"/", "/list"})
    public String liste(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "20") int size) {
//        model.addAttribute("rdvs", Data.listeRDV);
        Page<RendezVous> list = rendezVousRepository.findAll(PageRequest.of(page, size));
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("rdvs", list);
        model.addAttribute("formSearch", new SearchFrom());
        model.addAttribute("specialites", specialiteRepository.findAll());
        return "rdv/list";
    }

    @RequestMapping()
    public String defaultt() {
        return "redirect:/rdv/list";
    }
    
    @GetMapping("/{id}/create")
    public String create(@PathVariable Long id, Model model) {
        RendezVous rv = new RendezVous();
        model.addAttribute("rendezVous", rv);
        model.addAttribute("patient", patientRepository.findById(id).get());
        model.addAttribute("spes",specialiteRepository.findAll());
        return "rdv/create";
    }
    
    @PostMapping("/{id}/save")
    public String save(@Valid RendezVous rv, @RequestParam Long idpatient, @RequestParam Long idspec, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "rdv/create";
        rv.setDossier(patientRepository.findById(idpatient).get().getDossier());
        rv.setSpecialite(specialiteRepository.findById(idspec).get());
        rendezVousRepository.save(rv);
        return "redirect:/rdv/list";
    }

}

@NoArgsConstructor
@AllArgsConstructor
@Data
class SearchFrom {
    String nom, prenom, dateCreation1, dateCreation2, dateRdv1, dateRdv2;
    String[] specialites;
//        Boolean genre;
}
