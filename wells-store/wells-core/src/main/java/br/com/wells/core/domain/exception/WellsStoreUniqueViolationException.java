package br.com.wells.core.domain.exception;

public class WellsStoreUniqueViolationException extends RuntimeException {

	public WellsStoreUniqueViolationException(String message, Throwable e) {
		super(message, e);
	}

	public WellsStoreUniqueViolationException(String message) {
		super(message);
	}

}
