package com.example.mentalhealth.repository;

import com.example.mentalhealth.models.Admin;
import com.example.mentalhealth.models.ApplicationUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository<Admin,Integer> {
    Admin findByUsername(String username);
}
