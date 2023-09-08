package com.rioinvest.authms.exceptions;

public class ErrorsSystem extends RuntimeException {

    public static class NotFoundException extends RuntimeException {
        public NotFoundException(String me) {
            super(me);
        }
    }

    public static class ForbiddenException extends RuntimeException {
        public ForbiddenException(String me) {
            super(me);
        }
    }

    public static class UnauthorizedException extends RuntimeException {
        public UnauthorizedException(String me) {
            super(me);
        }
    }

    public static class BadRequestException extends RuntimeException {
        public BadRequestException(String me) {
            super(me);
        }
    }
}
