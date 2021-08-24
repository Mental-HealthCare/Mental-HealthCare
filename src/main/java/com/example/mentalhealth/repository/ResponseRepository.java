package com.example.mentalhealth.repository;

import com.example.mentalhealth.models.Consultation;
import com.example.mentalhealth.models.Response;
import org.springframework.data.repository.CrudRepository;

public interface ResponseRepository extends CrudRepository<Response , Integer> {
    public Iterable<Response> findAllByConsultation(Consultation consultation);
}
