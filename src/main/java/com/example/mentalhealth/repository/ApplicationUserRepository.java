package com.example.mentalhealth.repository;

import com.example.mentalhealth.models.ApplicationUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserRepository extends CrudRepository<ApplicationUser,Integer> {
    ApplicationUser findByUsername(String username);
}