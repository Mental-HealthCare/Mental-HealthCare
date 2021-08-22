package com.example.mentalhealth.controllers;

import com.example.mentalhealth.models.ApplicationUser;
import com.example.mentalhealth.models.Therapists;
import com.example.mentalhealth.repository.ApplicationUserRepository;
import com.example.mentalhealth.repository.ConsultationRepository;
import com.example.mentalhealth.repository.ResponseRepository;
import com.example.mentalhealth.repository.TherapistsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.text.ParseException;
import java.util.Objects;

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


    @GetMapping("/myProfile")
    public String getUserConsultation(Principal p, Model m){
        ApplicationUser user = applicationUserRepository.findByUsername(p.getName());
        m.addAttribute("profileUser",user);
        m.addAttribute("Consultations",user.getConsultation());
        return "myProfile";
    }

    @GetMapping("/useNameExist")
    public String getUserIfUseNameExist(@RequestParam("id") int id,Principal p, Model m){
        if(id == 400){
        System.out.println(id+ " ==================");
        ApplicationUser user = applicationUserRepository.findByUsername(p.getName());
        m.addAttribute("profileUser",user);
        m.addAttribute("Consultations",user.getConsultation());
        m.addAttribute("userNameExist" , true);
        }
        return "myProfile";
    }

//    @RequestMapping(value ="/editProfile" , method = RequestMethod.PUT , consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
//    public ResponseEntity<ApplicationUser> editProfile(@RequestBody ApplicationUser user, Principal p) throws ParseException {
//        System.out.println("============");
//    ApplicationUser applicationUser = applicationUserRepository.findByUsername(p.getName());
//    applicationUser.setFirstname(user.getFirstname());
//    applicationUser.setLastname(user.getLastname());
//    final ApplicationUser editedUser = applicationUserRepository.save(applicationUser);
//    return ResponseEntity.ok(editedUser);
//    }


    @RequestMapping(value ="/editProfile" , method = RequestMethod.GET)
    public RedirectView editProfile(@RequestParam("username") String username ,
                                      @RequestParam("firstname") String firstname,
                                      @RequestParam("lastname") String lastname,
                                      @RequestParam("dateOfBirth") String dateOfBirth,
                                      @RequestParam("image") String image,
                                      @RequestParam("country") String country,
                                      Principal principal, Model model){
        ApplicationUser user = applicationUserRepository.findByUsername(principal.getName());
        Therapists therapists = therapistsRepository.findByUsername(user.getUsername());
        System.out.println(username + "========");
        System.out.println(principal.getName()+"++++++++++++++++");
        if(Objects.equals(username, principal.getName())){
            System.out.println("Hello world");
        } else if(user != null || therapists != null){
            System.out.println(" ++++++++++++++++++++++++++++++++++++++ ");
            return new RedirectView("/useNameExist?id=400");
        }
        user.setUsername(username);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setDateOfBirth(dateOfBirth);
        user.setImage(image);
        user.setCountry(country);
        final ApplicationUser newUser = applicationUserRepository.save(user);
        return new RedirectView("/myProfile");
    }


    //    @PostMapping("/requestConsultation")
//    public RedirectView addConsultation() {
//
//        return new RedirectView();
//    }
//
}
