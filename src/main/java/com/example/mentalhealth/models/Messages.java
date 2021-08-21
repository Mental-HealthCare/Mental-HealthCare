package com.example.mentalhealth.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String body;
    @CreationTimestamp
    private Date time;
    private String sender;

    @ManyToOne
    private Chat chat;  //relationship with Chat

    public Messages() {
    }

    public Messages(String body, String sender,Chat chat) {
        this.body = body;
        this.sender = sender;
        this.chat = chat;
    }

    public Integer getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }
}
