package br.com.wells.core.domain.exception;

public class WellsStoreDataIntegrityViolationException extends RuntimeException {

	public WellsStoreDataIntegrityViolationException(String message, Throwable e) {
		super(message, e);
	}

	public WellsStoreDataIntegrityViolationException(String message) {
		super(message);
	}

}
