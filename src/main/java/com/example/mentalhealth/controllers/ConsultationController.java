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
    public RedirectView addConsultation(Principal p, Model m, @RequestParam Integer TherapistId, @RequestParam String body) {
        Consultation newConsultation = new Consultation(body, false, applicationUserRepository.findByUsername(p.getName()), therapistsRepository.findById(TherapistId).get());
        consultationRepository.save(newConsultation);
        return new RedirectView("/myProfile");
    }

    @RequestMapping("/DeleteOneConsultation/{consultationId}")
    public RedirectView deleteEmployee(@PathVariable Integer consultationId) {
        consultationRepository.deleteById(consultationId);
        return new RedirectView("/myProfile");
    }

    @GetMapping("/showOneConsultation/{consultationId}")
    public String showOneConsultation(Principal p, @PathVariable Integer consultationId, Model m) {
        System.out.println(consultationId +"  ++++++++++++++++++++");
        Consultation oneConsultation = consultationRepository.findById(consultationId).get();
        m.addAttribute("oneConsultation", oneConsultation);
        m.addAttribute("testButton", true);
        System.out.println(consultationId +"  ++++++++++++++++++++");
        if (applicationUserRepository.findByUsername(p.getName()) != null) {
            System.out.println("++++++++++++++++++++++++=");
            m.addAttribute("testButton", false);
            m.addAttribute("updateConsultationButton", true);
        }
        return "oneConsultation";
    }

    @RequestMapping(value = "/responseConsultation/{consultationId}", method = RequestMethod.GET)
    public RedirectView responseConsultation(@PathVariable Integer consultationId) {
        Consultation oneConsultation = consultationRepository.findById(consultationId).get();
        oneConsultation.setTaken(true);
        consultationRepository.save(oneConsultation);
        return new RedirectView("/showOneConsultation/" + consultationId);
    }

    @GetMapping("/updateConsultation/{consultationId}")
    public String updateConsultation(@PathVariable Integer consultationId, Model m) {
        Consultation oneConsultation = consultationRepository.findById(consultationId).get();
        m.addAttribute("oneConsultation", oneConsultation);
        m.addAttribute("testButton", false);
        m.addAttribute("updateConsultationButton", true);
        m.addAttribute("showUpdateConsultationForm", true);
        return "oneConsultation";
    }

    @RequestMapping(value = "/editConsultation/{consultationId}", method = RequestMethod.GET)
    public RedirectView editProfile(@RequestParam("body") String body, @PathVariable Integer consultationId) {
        Consultation oneConsultation = consultationRepository.findById(consultationId).get();
        oneConsultation.setBody(body);
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


    @GetMapping("/deleteResponse/{id}")
    public RedirectView deleteResponseFunction(@PathVariable Integer id , @RequestParam("userId") String usrId){
        responseRepository.deleteById(id);
        System.out.println(usrId + "   tttttttttttttttttttttt");
        return new RedirectView("/showOneConsultation/"+usrId);
    }

    @GetMapping("updateResponse")
    public void updateResponse(){

    }


}