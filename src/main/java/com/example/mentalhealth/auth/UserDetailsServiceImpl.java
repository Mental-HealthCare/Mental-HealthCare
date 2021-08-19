package com.example.mentalhealth.auth;

import com.example.mentalhealth.models.ApplicationUser;
import com.example.mentalhealth.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
//        if (applicationUser == null) {
//            throw new UsernameNotFoundException("Dose not exist");
//        }
        return null;
    }
}
