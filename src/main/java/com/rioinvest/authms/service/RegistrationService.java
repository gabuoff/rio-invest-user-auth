package com.rioinvest.authms.service;

import com.rioinvest.authms.domain.register.RegistrationRequest;
import com.rioinvest.authms.domain.user.User;
import com.rioinvest.authms.enums.ErrorMessages;
import com.rioinvest.authms.exceptions.ErrorsSystem;
import com.rioinvest.authms.mapper.UserRegistrationMapper;
import com.rioinvest.authms.repository.UserRepository;
import com.rioinvest.authms.utils.ConvertDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public void registerUser(RegistrationRequest reg) {
        Date dateOfBirth = ConvertDate.converDate(reg.dateOfBirth());
        String encodedPassword = passwordEncoder.encode(reg.password());
        User user = UserRegistrationMapper.userMapper(reg, dateOfBirth, encodedPassword);
        validar(user.getUsername(), user.getEmail(), user.getCpf());
        userRepository.save(user);
    }

    private void validar(String username, String email, String cpf){
        UserDetails existingUserByUsername = userRepository.findByUsername(username);
        if (existingUserByUsername != null) {
            throw new ErrorsSystem.BadRequestException(ErrorMessages.NAME_ALREADY_CAUGHT.getMessage());
        }
        User existingUserByEmail = userRepository.findByEmail(email);
        if (existingUserByEmail != null) {
            throw new ErrorsSystem.BadRequestException(ErrorMessages.EMAIL_ALREADY_CAUGHT.getMessage());
        }
        User existingUserByCPF = userRepository.findByCpf(cpf);
        if (existingUserByCPF != null) {
            throw new ErrorsSystem.BadRequestException(ErrorMessages.CPF_ALREADY_CAUGHT.getMessage());
        }
    }
}
