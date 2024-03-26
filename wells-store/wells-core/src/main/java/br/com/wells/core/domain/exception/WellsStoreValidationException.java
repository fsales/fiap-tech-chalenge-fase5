package br.com.wells.core.domain.exception;

public class WellsStoreValidationException extends RuntimeException {

	public WellsStoreValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public WellsStoreValidationException(String message) {
		super(message);
	}

}