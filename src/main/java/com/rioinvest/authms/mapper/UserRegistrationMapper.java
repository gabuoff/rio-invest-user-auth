package com.rioinvest.authms.mapper;

import java.util.Date;

import com.rioinvest.authms.domain.register.RegistrationRequest;
import com.rioinvest.authms.domain.user.User;

public class UserRegistrationMapper {
     public static User userMapper(RegistrationRequest reg, Date dateOfBirth, String encodedPassword){
        
    return User.builder()
            .username(reg.username())
            .email(reg.email())
            .password(encodedPassword)
            .cpf(reg.cpf())
            .fullName(reg.fullname())
            .dateOfBirth(dateOfBirth)
            .active(true)
            .locked(false)
            .build();
    }
}
