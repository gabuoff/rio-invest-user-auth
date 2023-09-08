package com.rioinvest.authms.domain;

import java.util.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegistrationRequest(
    @NotBlank(message = "Username must not be blank")  
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    String username,

    @NotBlank(message = "Password must not be blank")
    @Size(min = 6, message = "Password must be at least 6 characters")
    String password,

    @NotBlank(message = "Email must not be blank")
    @Size(max = 100, message = "Email must be at least 100 characters")
    @Email(message = "Invalid email format")
    String email,

    @NotBlank(message = "CPF must not be blank")
    @Size(min = 11,max = 14, message = "CPF must be between 11 and 14 characters")
    String cpf,

    @NotBlank(message = "Fullname is requested")
    @Size(max = 100, message = "Fullname must be less than or equal to 100 characters")
    String fullname,
    
    String dateOfBirth
) {}
