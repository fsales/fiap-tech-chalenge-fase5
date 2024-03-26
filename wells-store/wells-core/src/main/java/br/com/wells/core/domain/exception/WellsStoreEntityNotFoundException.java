package br.com.wells.core.domain.exception;

public class WellsStoreEntityNotFoundException extends RuntimeException {

	public WellsStoreEntityNotFoundException(String message) {
		super(message);
	}

	public WellsStoreEntityNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
