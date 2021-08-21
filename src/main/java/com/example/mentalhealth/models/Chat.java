package com.example.mentalhealth.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String chatName;

    @OneToMany (mappedBy =  "chat")     //relationship with Messages
    private List<Messages> messages;

    public Chat() {}

    public Chat(String chatName) {
        this.chatName = chatName;
    }

    public Integer getId() {
        return id;
    }

    public List<Messages> getMessages() {
        return messages;
    }

    public void setMessages(List<Messages> messages) {
        this.messages = messages;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }
}
