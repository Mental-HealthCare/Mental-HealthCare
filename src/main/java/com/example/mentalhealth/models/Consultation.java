package com.example.mentalhealth.models;

import org.hibernate.annotations.CreationTimestamp;

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
    private String body;
    @CreationTimestamp
    private Date time;
    private boolean taken;
    @ManyToOne       //relationship with patient
    private ApplicationUser applicationUser;

    @ManyToOne        //relationship with therapists
    private Therapists therapists;

    @OneToMany (mappedBy = "consultation")   //relationship with response
    private List<Response> responses ;

    //default constructor
    public Consultation() {}

    //constructor
    public Consultation(String body, Date time, boolean taken, ApplicationUser applicationUser, Therapists therapists) {
        this.body = body;
        this.time = time;
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
}
