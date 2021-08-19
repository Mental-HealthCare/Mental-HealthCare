package com.example.mentalhealth.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String body;
    @CreationTimestamp
    private Date time;

    @ManyToOne
    private Consultation consultation;

    public Response() {}

    public Response(String body, Date time , Consultation consultation) {
        this.body = body;
        this.time = time;
        this.consultation=consultation;
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
}

