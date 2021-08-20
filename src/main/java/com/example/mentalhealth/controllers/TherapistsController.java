package com.example.mentalhealth.controllers;

import com.example.mentalhealth.models.ApplicationUser;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
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


//    @GetMapping("/signup")
//    public String getSignUpPage(){return "signup.html";}


    @GetMapping("/kkkk")
    public String  addNewUser () {
        Therapists therapists = new Therapists("z" , bCryptPasswordEncoder.encode("z") , "z" , "z" ,"z" ,"z","z","z" , 1);
        therapistsRepository.save(therapists);
        return "home.html";
    }



//    @GetMapping("/login")
//    public String getLoginPage(Principal p, Model model){
//        try{
//            model.addAttribute("userData",p.getName());
//        }catch (NullPointerException e){
//            model.addAttribute("userData","");
//        }
//        return "login.html";
//    }

}
