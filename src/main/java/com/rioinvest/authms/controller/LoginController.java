package com.rioinvest.authms.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rioinvest.authms.domain.login.LoginRequest;
import com.rioinvest.authms.domain.user.User;
import com.rioinvest.authms.service.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class LoginController {
    
    @Autowired
    private TokenService tokenService;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid LoginRequest req) {
        var token = new UsernamePasswordAuthenticationToken(req.username(), req.password());
        var auth = authenticationManager.authenticate(token);

        Map<String, String> response = new HashMap<>();
        var tokenResponse =  tokenService.generateToken((User) auth.getPrincipal());
        response.put("token", tokenResponse);
        return ResponseEntity.ok(response);
    }
}
