package br.com.wells.app.usecases.security.exception;

public class TokenGenerationException extends RuntimeException {

    public TokenGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}