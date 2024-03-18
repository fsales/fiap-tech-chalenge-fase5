package br.com.wells.app.infrastructure.config.spring.security.jwt.exception;

public class TokenValidationException extends RuntimeException {

	public TokenValidationException(String message, Throwable cause) {
		super(message, cause);
	}

}
