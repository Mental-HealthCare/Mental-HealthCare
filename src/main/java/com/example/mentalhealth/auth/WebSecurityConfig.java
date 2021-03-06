package com.example.mentalhealth.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }


    @Override      // using to set password encoder for application
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable().csrf().disable().authorizeRequests().antMatchers("/login", "/signup", "/", "/aware", "/signupTherapists", "/signupUser" , "/allTherapists", "/css/*.css" , "/css/**","/static/**", "/resources/**","/.*.css", "/images/**", "/.*.js" , "/*.js" , "/js/**" , "/plugins/**" , "/about", "/aware1","/aware2","/aware3","/aware4" ).permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login").loginProcessingUrl("/perform_login").defaultSuccessUrl("/", true).failureUrl("/error").and().logout().logoutUrl("/perform_logout").deleteCookies("JSESSIONID");
    }
}
