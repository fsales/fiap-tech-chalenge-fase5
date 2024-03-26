package br.com.wells.wellsgateway.infrastructure.spring.cloud.gateway.filter.exception;

public class TokenValidationException extends RuntimeException {

	public TokenValidationException(String message) {
		super(message);
	}

	public TokenValidationException(String message, Throwable cause) {
		super(message, cause);
	}

}