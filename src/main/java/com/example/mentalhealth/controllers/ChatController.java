package com.example.mentalhealth.controllers;

import com.example.mentalhealth.models.ApplicationUser;
import com.example.mentalhealth.models.Chat;
import com.example.mentalhealth.models.Consultation;
import com.example.mentalhealth.models.Messages;
import com.example.mentalhealth.repository.ApplicationUserRepository;
import com.example.mentalhealth.repository.ChatRepository;
import com.example.mentalhealth.repository.ConsultationRepository;
import com.example.mentalhealth.repository.MessagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Controller
public class ChatController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Autowired
    ConsultationRepository consultationRepository;
    @Autowired
    ChatRepository chatRepository;
    @Autowired
    MessagesRepository messagesRepository;

    @GetMapping("/chat")
    public String getChat(Principal p, Model m) {
        Chat chat = chatRepository.findById(1).get();
        ApplicationUser user = applicationUserRepository.findByUsername(p.getName());
        List users = (List) applicationUserRepository.findAll();
        if (users.size() < 10) {
            m.addAttribute("listOfUsers", users);
        } else {
            Collections.shuffle(users);
            int randomSeriesLength = 9;
            List randomSeries = users.subList(0, randomSeriesLength);
            m.addAttribute("listOfUsers", randomSeries);
        }
        m.addAttribute("numberOfMessage", chat.getMessages().size());
        m.addAttribute("chatData", chat.getMessages());
//        m.addAttribute("chatData", chat.getMessages().get(1).getTime());
//        m.addAttribute("chatData", chat.getMessages().get(1).getSender().getFirstname());
        m.addAttribute("userData", user);
        m.addAttribute("loggedInUser", p.getName());
        if (user != null){
            m.addAttribute("messagesSender" , true);
        }
//        if(Objects.equals(p.getName(), chat.getMessages().get(1).getSender().getFirstname()))
        return "chat.html";
    }

    @PostMapping("/sentMassage")
    public RedirectView sentMassage(Principal p, @RequestParam String body) {
        Chat chat = chatRepository.findById(1).get();
        ApplicationUser user = applicationUserRepository.findByUsername(p.getName());
        Messages newMessage = new Messages(body, user, chat);
        messagesRepository.save(newMessage);
        return new RedirectView("/chat");
    }
}
