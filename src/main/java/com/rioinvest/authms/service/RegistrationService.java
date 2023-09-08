package com.rioinvest.authms.service;

import com.rioinvest.authms.domain.User;
import com.rioinvest.authms.enums.ErrorMessages;
import com.rioinvest.authms.exceptions.ErrorsSystem;
import com.rioinvest.authms.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public RegistrationService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(User user) {
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
