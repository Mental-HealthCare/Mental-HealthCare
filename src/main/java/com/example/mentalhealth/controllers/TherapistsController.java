package com.example.mentalhealth.controllers;

import com.example.mentalhealth.models.*;
import com.example.mentalhealth.repository.*;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class TherapistsController {

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

    @PostMapping("/signupTherapists")
    public RedirectView addNewUser(@ModelAttribute Therapists user) {
        ApplicationUser existUserName = applicationUserRepository.findByUsername(user.getUsername());
        if (existUserName == null) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//            user.setEnabled(true);
            therapistsRepository.save(user);
        } else {     // handling error message exist username
            System.out.println("Exist username");
        }
        return new RedirectView("/login");
    }

    @GetMapping("/allTherapists")
    public String getAllTherapists(Model model) {
        Iterable allTherapists = therapistsRepository.findAllByIsEnabled(true);
        model.addAttribute("allTherapists", allTherapists);
        return "allTherapists.html";
    }

    @GetMapping("/therapistsProfile/{therapistId}")
    public String showTherapistsProfile(@PathVariable Integer therapistId, Model m, Principal p) {
        if (applicationUserRepository.findByUsername(p.getName()) != null) {
            m.addAttribute("addConsultationButton", true);
        }
        Therapists oneTherapist = therapistsRepository.findById(therapistId).get();
        m.addAttribute("profileUser", oneTherapist);
        return "therapistsProfile";
    }

    @GetMapping("/showAddConsultationForm/{therapistId}")
    public String showAddConsultationForm(@PathVariable Integer therapistId, Model m) {
        m.addAttribute("addConsultationButton", true);
        m.addAttribute("showForm", true);
        Therapists oneTherapist = therapistsRepository.findById(therapistId).get();
        m.addAttribute("profileUser", oneTherapist);
        return "therapistsProfile";
    }

    @PostMapping("/addConsultationTherapist/{therapistId}")
    public RedirectView addConsultationTherapist(@RequestParam("body") String body, @PathVariable Integer therapistId, Principal p) {
        Therapists oneTherapist = therapistsRepository.findById(therapistId).get();
        ApplicationUser user = applicationUserRepository.findByUsername(p.getName());
        Consultation newConsultation = new Consultation(body, false, user, oneTherapist);
        consultationRepository.save(newConsultation);
        return new RedirectView("/therapistsProfile/" + therapistId);
    }
}
