package com.example.mentalhealth.controllers;

import com.example.mentalhealth.models.ChatMessage;
import com.example.mentalhealth.repository.WebSocketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.time.LocalTime;

@Controller
public class WebSocketController {

    @Autowired
    WebSocketRepo webSocketRepo;

    @MessageMapping("/hello") // the endpoint which will be called from the client
    @SendTo("/topic/greetings")
    public String greet(@Payload String message, Principal p ) throws Exception{

        int hour = LocalTime.now().getHour();
        int minuts = LocalTime.now().getMinute();
        int second = LocalTime.now().getSecond();
        System.out.println(hour+ ":"+ minuts);
        String time = hour+ ":"+ minuts+ ":"+ second;
//        Thread.sleep(2000);
        ChatMessage chatMessage = new ChatMessage(p.getName(), message, time );
        webSocketRepo.save(chatMessage);

        return message + " #$% "+p.getName() + " #$% " +time;
    }



    @GetMapping("/getMessages")
    public String getMessages( Model model){
        Iterable allData = webSocketRepo.findAll();
        model.addAttribute("webSocketRepo" , allData);
        return "index.html";
    }

}