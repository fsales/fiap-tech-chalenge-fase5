package br.com.wells.core.domain.usuario.exception;

public class UsernameUniqueViolationException extends RuntimeException {

	public UsernameUniqueViolationException(String message) {
		super(message);
	}

}
