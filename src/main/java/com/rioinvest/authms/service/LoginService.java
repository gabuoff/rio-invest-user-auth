package com.rioinvest.authms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.rioinvest.authms.repository.UserRepository;

@Service
public class LoginService implements UserDetailsService {
    
    @Autowired
    private UserRepository repos;


    @Override
    public UserDetails loadUserByUsername(String username){
        return repos.findByUsername(username);
    }
}
