package br.com.wells.wellsgateway.infrastructure.spring.cloud.gateway.filter.exception;

public class MissingAuthorizationHeaderException extends RuntimeException {

	public MissingAuthorizationHeaderException(String message) {
		super(message);
	}

	public MissingAuthorizationHeaderException(String message, Throwable cause) {
		super(message, cause);
	}

}