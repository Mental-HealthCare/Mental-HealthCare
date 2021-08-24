package com.example.mentalhealth.repository;

import com.example.mentalhealth.models.Consultation;
import com.example.mentalhealth.models.Therapists;
import org.springframework.data.repository.CrudRepository;

public interface ConsultationRepository extends CrudRepository<Consultation , Integer> {
    public Iterable<Consultation> findAllByTaken(boolean taken);
}
