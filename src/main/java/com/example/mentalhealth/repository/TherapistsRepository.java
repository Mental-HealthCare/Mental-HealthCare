package com.example.mentalhealth.repository;

import com.example.mentalhealth.models.ApplicationUser;
import com.example.mentalhealth.models.Therapists;
import org.springframework.data.repository.CrudRepository;

public interface TherapistsRepository extends CrudRepository<Therapists ,Integer> {
    public Therapists findByUsername(String username);
    public Iterable<Therapists> findAllByIsEnabled(boolean isEnabled);


}
