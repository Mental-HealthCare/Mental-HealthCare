package com.example.mentalhealth.controllers;

import com.example.mentalhealth.models.ApplicationUser;
import com.example.mentalhealth.models.Therapists;
import com.example.mentalhealth.repository.ApplicationUserRepository;
import com.example.mentalhealth.repository.TherapistsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class ApplicationUserController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    TherapistsRepository therapistsRepository;

    @PostMapping("/signupUser")
    public RedirectView addNewUser(@ModelAttribute ApplicationUser user) {
        Therapists existUserName = therapistsRepository.findByUsername(user.getUsername());
        if (existUserName == null) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            applicationUserRepository.save(user);
        } else {    // handling error message exist username
            System.out.println("Exist username");
        }
        return new RedirectView("/login");
    }

    @GetMapping("/myProfile")
    public String getUserConsultation(Principal p, Model m) {

        ApplicationUser user = applicationUserRepository.findByUsername(p.getName());

        if (user == null) {
            Therapists therapists = therapistsRepository.findByUsername(p.getName());
            m.addAttribute("profileUser", therapists);
            m.addAttribute("Consultations", therapists.getConsultation());
        } else {
            m.addAttribute("profileUser", user);
            m.addAttribute("Consultations", user.getConsultation());
            m.addAttribute("applicationUser", true);
            // get all therapists for consultation adding
            Iterable allTherapists = therapistsRepository.findAll();
            m.addAttribute("allTherapists", allTherapists);
        }
        return "myProfile";
    }
}

