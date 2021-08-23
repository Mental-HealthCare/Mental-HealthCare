package com.example.mentalhealth.controllers;

import com.example.mentalhealth.models.ApplicationUser;
import com.example.mentalhealth.models.Therapists;
import com.example.mentalhealth.repository.ApplicationUserRepository;
import com.example.mentalhealth.repository.TherapistsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(value = "/editProfile", method = RequestMethod.GET)
    public RedirectView editProfile(@RequestParam("firstname") String firstname,
                                    @RequestParam("lastname") String lastname,
                                    @RequestParam("dateOfBirth") String dateOfBirth,
                                    @RequestParam("image") String image,
                                    @RequestParam("country") String country,
                                    @RequestParam("specializedIn") String specializedIn,
                                    @RequestParam("experiences") String experiences,
                                    @RequestParam("numOfSessions") Integer numOfSessions,
                                    Principal principal, Model model) {

        ApplicationUser user = applicationUserRepository.findByUsername(principal.getName());

        if (user == null) {
            Therapists therapists = therapistsRepository.findByUsername(principal.getName());
            therapists.setFirstname(firstname);
            therapists.setLastname(lastname);
            therapists.setImage(image);
            therapists.setCountry(country);
            therapists.setSpecializedIn(specializedIn);
            therapists.setExperiences(experiences);
            therapists.setNumOfSessions(numOfSessions);
            therapistsRepository.save(therapists);
        } else {
            user.setFirstname(firstname);
            user.setLastname(lastname);
            user.setDateOfBirth(dateOfBirth);
            user.setImage(image);
            user.setCountry(country);
            applicationUserRepository.save(user);
        }
        return new RedirectView("/myProfile");
    }
}

