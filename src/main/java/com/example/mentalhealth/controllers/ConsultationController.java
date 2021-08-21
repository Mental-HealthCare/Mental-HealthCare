package com.example.mentalhealth.controllers;

import com.example.mentalhealth.models.Consultation;
import com.example.mentalhealth.models.Response;
import com.example.mentalhealth.models.Therapists;
import com.example.mentalhealth.repository.ApplicationUserRepository;
import com.example.mentalhealth.repository.ConsultationRepository;
import com.example.mentalhealth.repository.ResponseRepository;
import com.example.mentalhealth.repository.TherapistsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class ConsultationController {

    @Autowired
    ConsultationRepository consultationRepository;
    @Autowired
    TherapistsRepository therapistsRepository;
    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Autowired
    ResponseRepository responseRepository;

    @PostMapping("/requestConsultation")
    public RedirectView addConsultation(Principal p, Model m, @RequestParam Integer TherapistId, @RequestParam String body) {
        Consultation newConsultation = new Consultation(body, false, applicationUserRepository.findByUsername(p.getName()), therapistsRepository.findById(TherapistId).get());
        consultationRepository.save(newConsultation);
        return new RedirectView("/myProfile");
    }

    @GetMapping("/showOneConsultation/{consultationId}")
    public String showOneConsultation(@PathVariable Integer consultationId, Model m) {
        Consultation oneConsultation = consultationRepository.findById(consultationId).get();
        m.addAttribute("oneConsultation", oneConsultation);
        return "oneConsultation";
    }

    @RequestMapping("/DeleteOneConsultation/{consultationId}")
    public RedirectView deleteEmployee(@PathVariable Integer consultationId) {
        consultationRepository.deleteById(consultationId);
        return new RedirectView("/myProfile");
    }

    @PostMapping("/addResponse")
    public RedirectView addResponse(Principal principal, @RequestParam Integer consultationId, @RequestParam String responseBody) {
        Therapists therapist = therapistsRepository.findByUsername(principal.getName());
        Response newResponse;
        if (therapist == null) {
            newResponse = new Response(responseBody, false, consultationRepository.findById(consultationId).get());
        } else {
            newResponse = new Response(responseBody, true, consultationRepository.findById(consultationId).get());
        }
        responseRepository.save(newResponse);
        return new RedirectView("/showOneConsultation/" + consultationId);
    }
}
