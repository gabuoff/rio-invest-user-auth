package com.rioinvest.authms.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rioinvest.authms.domain.RegistrationRequest;
import com.rioinvest.authms.domain.User;
import com.rioinvest.authms.enums.AppMessages;
import com.rioinvest.authms.service.RegistrationService;

@RestController
@RequestMapping("/auth")
public class RegisterController {
    
    @Autowired
    private RegistrationService registerService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegistrationRequest reg) {
        Date dateOfBirth = converDate(reg.dateOfBirth());
        
        User user = userMapper(reg, dateOfBirth);
        registerService.registerUser(user);
        return new ResponseEntity<>(AppMessages.REGISTER_SUCCESS.getMessage(), HttpStatus.CREATED);
    }

    private User userMapper(RegistrationRequest reg, Date dateOfBirth){
    return User.builder()
            .username(reg.username())
            .email(reg.email())
            .password(reg.password())
            .cpf(reg.cpf())
            .fullName(reg.fullname())
            .dateOfBirth(dateOfBirth)
            .build();
    }

    private Date converDate(String date){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
             return dateFormat.parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid dateOfBirth format. Please use 'dd/MM/yyyy' format.", e);
        }
    }

}