package com.myboot.myboot.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

import com.mysql.cj.protocol.AuthenticationProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
   
    @Autowired
    UserDetailsService authDetailsService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception{
        security.csrf(Customizer -> Customizer.disable()); // disable csrf
        security.authorizeHttpRequests(request -> request.anyRequest().authenticated()); // every request shold auth
        security.httpBasic(Customizer.withDefaults()); // for all api requests. like api testing tools. 
        security.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // creates stateless session
        return security.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        provider.setUserDetailsService(authDetailsService);
        return (AuthenticationProvider) provider;
    }
}
