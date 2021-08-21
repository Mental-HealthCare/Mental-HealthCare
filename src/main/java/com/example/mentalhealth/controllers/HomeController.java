package com.example.mentalhealth.controllers;

import com.example.mentalhealth.models.Consultation;
import com.example.mentalhealth.repository.ApplicationUserRepository;
import com.example.mentalhealth.repository.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Autowired
    ConsultationRepository consultationRepository;

    @GetMapping("/")
    public String getHome(Principal p, Model model){
//        Consultation consultation = consultationRepository.findById(1).get();
//        model.addAttribute("therapistsName",consultation.getTherapists().getFirstname());
//        model.addAttribute("usersName",consultation.getApplicationUser().getFirstname());
//        model.addAttribute("response",consultation.getResponses());
//        model.addAttribute("userData",p.getName());

        return "home.html";
    }


}
