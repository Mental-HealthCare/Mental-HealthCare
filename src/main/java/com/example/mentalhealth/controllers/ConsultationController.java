package com.example.mentalhealth.controllers;

import com.example.mentalhealth.models.ApplicationUser;
import com.example.mentalhealth.models.Therapists;
import com.example.mentalhealth.repository.ApplicationUserRepository;
import com.example.mentalhealth.repository.ConsultationRepository;
import com.example.mentalhealth.repository.ResponseRepository;
import com.example.mentalhealth.repository.TherapistsRepository;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
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

    @GetMapping("/newUserName")
    public String getUserIfUseNameNotExist(@RequestParam("userName") String userName,Principal p, Model m){
        ApplicationUser user = applicationUserRepository.findByUsername(userName);
        m.addAttribute("profileUser",user);
        m.addAttribute("Consultations",user.getConsultation());
        return "myProfile";
    }

    @GetMapping("/useNameExist")
    public String getUserIfUseNameExist(@RequestParam("id") int id,Principal p, Model m){
        if(id == 400){
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
                                    Principal principal, Model model, Authentication auth ){


        ApplicationUser user = applicationUserRepository.findByUsername(principal.getName());

        ApplicationUser checkUser =applicationUserRepository.findByUsername(username);
        Therapists therapists = therapistsRepository.findByUsername(username);

        System.out.println(user + "=========");
        System.out.println(therapists + "=========");
        try {
            if (Objects.equals(username, principal.getName())) {
                System.out.println("Hello world");
            } else if (checkUser != null) {
                System.out.println(" ++++++++++++++++++++++++++++++++++++++ ");
                return new RedirectView("/useNameExist?id=400");
            } else if (therapists != null) {
                System.out.println(" *****************************");
                return new RedirectView("/useNameExist?id=400");
            }else {
                System.out.println("lk");
            }
            user.setUsername(username);
            user.setFirstname(firstname);
            user.setLastname(lastname);
            user.setDateOfBirth(dateOfBirth);
            user.setImage(image);
            user.setCountry(country);
            final ApplicationUser newUser = applicationUserRepository.save(user);
            return new RedirectView("/newUserName?userName=" + username);
        }catch (NullPointerException e){
            System.out.println("gfffg");
        }
        return new RedirectView("/newUserName");
    }

}
