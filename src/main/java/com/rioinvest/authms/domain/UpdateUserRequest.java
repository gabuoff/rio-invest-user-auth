package com.rioinvest.authms.domain;


import lombok.Data;

@Data
public class UpdateUserRequest {

    private String oldUsername, newUsername, oldPassword, newPassword, oldEmail, newEmail;
}
