package com.rioinvest.authms.enums;

public enum AppMessages {

    LOGIN_SUCCESS("Login bem-sucedido"),
    REGISTER_SUCCESS("Registro bem-sucedido");

    private final String message;

    AppMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}