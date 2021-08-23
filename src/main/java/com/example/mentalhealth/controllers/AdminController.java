package com.example.mentalhealth.controllers;

import com.example.mentalhealth.models.Admin;
import com.example.mentalhealth.models.ApplicationUser;
import com.example.mentalhealth.models.Consultation;
import com.example.mentalhealth.models.Therapists;
import com.example.mentalhealth.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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


}
