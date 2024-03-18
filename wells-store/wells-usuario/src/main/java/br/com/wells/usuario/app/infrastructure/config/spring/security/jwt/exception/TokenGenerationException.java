package br.com.wells.usuario.app.infrastructure.config.spring.security.jwt.exception;

public class TokenGenerationException extends RuntimeException {

	public TokenGenerationException(String message, Throwable cause) {
		super(message, cause);
	}

}