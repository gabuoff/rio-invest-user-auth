package com.rioinvest.authms.enums;

public enum ErrorMessages {

    INCORRECT_PASSWORD("Senha incorreta"),
    USER_NOT_FOUND("Usuário não encontrado"),
    ACCOUNT_LOCKED_RESET("Sua conta foi bloqueada. Por favor, redefina sua senha."),
    NAME_ALREADY_CAUGHT("Nome de usuário já existe."),
    EMAIL_ALREADY_CAUGHT("E-mail já registrado."),
    CPF_ALREADY_CAUGHT("CPF já registrado."),
    ACCOUNT_LOCKED_ATTEMPTS("Sua conta foi bloqueada devido a tentativas de login falhadas. Por favor, redefina sua senha.");
    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}