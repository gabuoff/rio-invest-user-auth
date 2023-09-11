package com.rioinvest.authms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rioinvest.authms.domain.register.RegistrationRequest;
import com.rioinvest.authms.enums.AppMessages;
import com.rioinvest.authms.service.RegistrationService;

@RestController
@RequestMapping("/api/auth")
public class RegisterController {
    
    @Autowired
    private RegistrationService registerService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegistrationRequest reg) {
        registerService.registerUser(reg);
        return new ResponseEntity<>(AppMessages.REGISTER_SUCCESS.getMessage(), HttpStatus.CREATED);
    }

   

    

}