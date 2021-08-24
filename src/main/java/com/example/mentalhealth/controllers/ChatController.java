package com.example.mentalhealth.controllers;

import com.example.mentalhealth.models.Chat;
import com.example.mentalhealth.models.Messages;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;

@Controller
public class ChatController {

    @MessageMapping("/hello")// the endpoint which will be called from the client
    @SendTo("/topic/greetings")
    public Chat greet(@Payload Messages messages) throws Exception{
        System.out.println(messages.getId());
        System.out.println(messages.hashCode());
        System.out.println( messages);
        System.out.println(messages.toString());
        System.out.println("============" + messages.getChat());
        System.out.println("============" + messages.getBody());
        System.out.println("============" + messages.getTime());

        Thread.sleep(2000);
        Chat chatMessage = new Chat("Hello," + HtmlUtils.htmlEscape(messages.getBody()));
        return chatMessage;
    }



    @GetMapping("/getMessages")
    public String getMessages(){
        return "index.html";
    }

    @GetMapping("/sendMessage")
    public String sendMessage(){
        return "sendMessage.html";
    }
}
