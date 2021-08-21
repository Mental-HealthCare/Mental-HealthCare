package com.example.mentalhealth.controllers;

import com.example.mentalhealth.models.Consultation;
import com.example.mentalhealth.repository.ApplicationUserRepository;
import com.example.mentalhealth.repository.ChatRepository;
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
    @Autowired
    ChatRepository chatRepository;

    @GetMapping("/signup")
    public String getSignUpPage(){return "signup.html";}

    @GetMapping("/login")
    public String getLoginPage(){
        return "login.html";
    }

    @GetMapping("/")
    public String getHome(Principal p, Model model){
//            Consultation consultation = consultationRepository.findById(1).get();
//            model.addAttribute("therapistsName",consultation.getTherapists().getFirstname());
//            model.addAttribute("usersName",consultation.getApplicationUser().getFirstname());
//            model.addAttribute("response",consultation.getResponses());
//            model.addAttribute("userData",p.getName());
//        model.addAttribute("messages", chatRepository.findById(1).get().getMessages() );
        return "home.html";
    }

//    @GetMapping("/chat")
//    public String chatPage(){
//        Chat firstChat = new Chat("first chat");
//        chatRepository.save(firstChat);
//        return "home.html";
//    }

//    @GetMapping("/kkkk")
//    public String  addNewUser (Principal p) {
//        Chat chat = chatRepository.findById(1).get();
//        Messages messages = new Messages("Fourth message" , p.getName() , chat);
//        messagesRepository.save(messages);
//        return "home.html";
//    }

}
