package com.example.mentalhealth.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@Entity
@EnableScheduling
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String body;
    @CreationTimestamp
    private Date time;
    private ApplicationUser sender;

    @ManyToOne
    private Chat chat;  //relationship with Chat


    public Messages(String body, ApplicationUser sender, Chat chat) {
        this.body = body;
        this.sender = sender;
        this.chat = chat;
    }

    public Messages(){}

    public ApplicationUser getSender() {
        return sender;
    }

    public void setSender(ApplicationUser sender) {
        this.sender = sender;
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



    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

}
