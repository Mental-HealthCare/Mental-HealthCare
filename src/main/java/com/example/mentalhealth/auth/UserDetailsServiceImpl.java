package com.example.mentalhealth.auth;

import com.example.mentalhealth.models.Admin;
import com.example.mentalhealth.models.ApplicationUser;
import com.example.mentalhealth.models.Therapists;
import com.example.mentalhealth.repository.AdminRepository;
import com.example.mentalhealth.repository.ApplicationUserRepository;
import com.example.mentalhealth.repository.TherapistsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Autowired
    TherapistsRepository therapistsRepository;
    @Autowired
    AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
        Therapists therapists = therapistsRepository.findByUsername(username);
        Admin admin = adminRepository.findByUsername(username);
        if (applicationUser == null && therapists == null && admin == null) {
            throw new UsernameNotFoundException("Dose not exist");
        }else if(applicationUser != null){
            return applicationUser;
        }else if(therapists != null){
            return therapists;
        }else{
            return admin;
        }
    }
}
