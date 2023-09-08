package com.rioinvest.authms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
   
@ExceptionHandler(ErrorsSystem.NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(ErrorsSystem.NotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ErrorsSystem.ForbiddenException.class)
    public ResponseEntity<String> handleForbiddenException(ErrorsSystem.ForbiddenException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ErrorsSystem.UnauthorizedException.class)
    public ResponseEntity<String> handleUnauthorizedException(ErrorsSystem.UnauthorizedException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    // Aqui, você pode adicionar mais manipuladores para outras exceções conforme necessário.

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e) {
        // Este é um manipulador genérico para qualquer exceção não especificamente tratada acima
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}