package com.myboot.myboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myboot.myboot.entity.AuthEntity;
import com.myboot.myboot.entity.AuthPrincipal;
import com.myboot.myboot.repository.AuthRepo;

@Service
public class MyAuthService implements UserDetailsService  {
     
    @Autowired
    AuthRepo authRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        AuthEntity authUser = authRepo.getByUsername(username);

        if(authUser == null) throw new UsernameNotFoundException(username);

        return new AuthPrincipal(authUser);

    } 

}
