package com.example.mentalhealth.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String subject;
    private String body;
    @CreationTimestamp
    private Date time;
    private boolean taken;
    @ManyToOne       //relationship with patient
    private ApplicationUser applicationUser;

    @ManyToOne        //relationship with therapists
    private Therapists therapists;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany (mappedBy = "consultation")   //relationship with response
    private List<Response> responses ;

    //default constructor
    public Consultation() {}

    //constructor
    public Consultation( String subject, String body, boolean taken, ApplicationUser applicationUser, Therapists therapists) {
        this.subject = subject;
        this.body = body;
        this.taken = taken;
        this.applicationUser = applicationUser;
        this.therapists = therapists;
    }


    // getter for id
    public Integer getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    public Therapists getTherapists() {
        return therapists;
    }

    public void setTherapists(Therapists therapists) {
        this.therapists = therapists;
    }

    public List<Response> getResponses() {
        return responses;
    }

    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }
}
