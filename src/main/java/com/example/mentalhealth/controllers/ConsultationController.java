package com.example.mentalhealth.controllers;

import com.example.mentalhealth.models.Consultation;
import com.example.mentalhealth.models.Response;
import com.example.mentalhealth.models.Therapists;
import com.example.mentalhealth.repository.ApplicationUserRepository;
import com.example.mentalhealth.repository.ConsultationRepository;
import com.example.mentalhealth.repository.ResponseRepository;
import com.example.mentalhealth.repository.TherapistsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/requestConsultation")
    public RedirectView addConsultation(Principal p, Model m, @RequestParam Integer TherapistId,@RequestParam String subject, @RequestParam String body) {
        Consultation newConsultation = new Consultation(subject, body, false, applicationUserRepository.findByUsername(p.getName()), therapistsRepository.findById(TherapistId).get());
        consultationRepository.save(newConsultation);
        return new RedirectView("/myProfile");
    }

    @RequestMapping("/DeleteOneConsultation/{consultationId}")
    public RedirectView deleteConsultation(@PathVariable Integer consultationId) {
        consultationRepository.deleteById(consultationId);
        return new RedirectView("/myProfile");
    }

    @RequestMapping(value = "/responseConsultation/{consultationId}", method = RequestMethod.GET)
    public RedirectView responseConsultation(@PathVariable Integer consultationId) {
        Consultation oneConsultation = consultationRepository.findById(consultationId).get();
        oneConsultation.setTaken(true);
        consultationRepository.save(oneConsultation);
        return new RedirectView("/showOneConsultation/" + consultationId);
    }

    @GetMapping("/showOneConsultation/{consultationId}")
    public String showOneConsultation(Principal p, @PathVariable Integer consultationId, Model m) {
        Consultation oneConsultation = consultationRepository.findById(consultationId).get();
        m.addAttribute("oneConsultation", oneConsultation);
        m.addAttribute("testButton", true);
        m.addAttribute("editResponseForm", false);
        if (applicationUserRepository.findByUsername(p.getName()) != null) {
            m.addAttribute("testButton", false);
            m.addAttribute("updateConsultationButton", true);
        }
        return "oneConsultation";
    }

    @RequestMapping(value = "/editConsultation/{consultationId}", method = RequestMethod.GET)
    public RedirectView editConsultation(@RequestParam("subject") String subject, @RequestParam("body") String body, @PathVariable Integer consultationId) {
        Consultation oneConsultation = consultationRepository.findById(consultationId).get();
        oneConsultation.setBody(body);
        oneConsultation.setSubject(subject);
        consultationRepository.save(oneConsultation);
        return new RedirectView("/showOneConsultation/" + consultationId);
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


    @RequestMapping("/DeleteResponse/{consultationId}/{responseId}")
    public RedirectView deleteResponse(@PathVariable Integer consultationId, @PathVariable Integer responseId) {
        responseRepository.deleteById(responseId);
        return new RedirectView("/showOneConsultation/" + consultationId);
    }

    @GetMapping("/showEditResponseForm/{consultationId}/{responseId}")
    public String showEditResponseForm(@PathVariable Integer consultationId, @PathVariable Integer responseId, Model m, Principal p) {
        Response response = responseRepository.findById(responseId).get();
        Consultation oneConsultation = consultationRepository.findById(consultationId).get();
        m.addAttribute("oneConsultation", oneConsultation);
        m.addAttribute("responseToEdit", response);
        m.addAttribute("testButton", true);
        m.addAttribute("editResponseForm", true);
        if (applicationUserRepository.findByUsername(p.getName()) != null) {
            m.addAttribute("testButton", false);
            m.addAttribute("updateConsultationButton", true);
        }
        return "oneConsultation";
    }

    @RequestMapping(value = "/editResponse/{consultationId}/{responseId}", method = RequestMethod.GET)
    public RedirectView editResponse(@PathVariable Integer consultationId, @PathVariable Integer responseId, @RequestParam("body") String body) {
        Response response = responseRepository.findById(responseId).get();
        response.setBody(body);
        responseRepository.save(response);
        return new RedirectView("/showOneConsultation/" + consultationId);
    }
}
