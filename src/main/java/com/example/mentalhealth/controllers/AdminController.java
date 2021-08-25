package com.example.mentalhealth.controllers;

import com.example.mentalhealth.models.*;
import com.example.mentalhealth.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
@Controller
public class AdminController {
    @Autowired
    ConsultationRepository consultationRepository;
    @Autowired
    TherapistsRepository therapistsRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Autowired
    ResponseRepository responseRepository;
    @Autowired
    MessagesRepository messagesRepository;
    @Autowired
    ChatRepository chatRepository;
    @Autowired
    AdminRepository adminRepository;

    @GetMapping("/dashboard")
    public String getAdminPanel(Model model, Principal p) {
        Iterable<Therapists> activatedTherapists = therapistsRepository.findAllByIsEnabled(true);
        Iterable<Therapists> pendingTherapists = therapistsRepository.findAllByIsEnabled(false);
        Iterable<Consultation> allConsultation = consultationRepository.findAll();
        Iterable<ApplicationUser> allpashints = applicationUserRepository.findAll();
        Admin admin = adminRepository.findByUsername(p.getName());
        model.addAttribute("adminData", admin);
        model.addAttribute("activatedTherapists", activatedTherapists);
        model.addAttribute("pendingTherapists", pendingTherapists);
        return "dashboard.html";
    }

    @PostMapping("/enableTherapist/{therapistId}")
    public RedirectView accepter(Principal p,@PathVariable Integer therapistId) {
        Therapists therapist =therapistsRepository.findById(therapistId).get();
        therapist.setEnabled();
        therapistsRepository.save(therapist);
        return new RedirectView("/dashboard");
    }

    @RequestMapping("/deletetherapist/{therapistId}")
    public RedirectView deleteConsultation(@PathVariable Integer therapistId) {
        therapistsRepository.deleteById(therapistId);
        return new RedirectView("/dashboard");
    }

}
