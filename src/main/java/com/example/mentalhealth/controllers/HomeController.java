package com.example.mentalhealth.controllers;

import com.example.mentalhealth.models.*;
import com.example.mentalhealth.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Controller
public class HomeController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Autowired
    ConsultationRepository consultationRepository;
    @Autowired
    ChatRepository chatRepository;
    @Autowired
    MessagesRepository messagesRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/signup")
    public String getSignUpPage() {
        return "signup.html";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login.html";
    }

    @GetMapping("/")
    public String getHome(Principal p, Model model) {
        List list = (List) chatRepository.findAll();
        if (list.size() == 0) {
            Chat chat = new Chat("Support_Group");

            Admin admin = new Admin("admin",bCryptPasswordEncoder.encode("admin"),"Light","House","https://shanghai-date.com/uploads/g/t/t/h/q2t34kjldqrqv0pl7ihh.png");
            chatRepository.save(chat);
            adminRepository.save(admin);
        }
//        if (p.getName()!= null && Objects.equals(p.getName(), "admin")){
//
//        }


        return "home.html";
    }


    @GetMapping("/aboutUs")
    public String getAboutUsPage(){
        return "aboutUs.html";
    }
//    @GetMapping("/chat")
//    public String chatPage(){
//        Chat firstChat = new Chat("first chat");
//        chatRepository.save(firstChat);
//        return "home.html";
//    }

    //    @GetMapping("/kkkk")
//    public String addNewUser(Principal p) {
//        Chat chat = chatRepository.findById(1).get();
//        ApplicationUser user = applicationUserRepository.findByUsername(p.getName());
//        Messages messages = new Messages("ZZZZZ message", user, chat);
//        messagesRepository.save(messages);
//        return "home.html";
//    }


}
