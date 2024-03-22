package br.com.wells.usuario.app.presentation.rest.controller.exception;

public class WellsAuthenticationException extends RuntimeException {

	public WellsAuthenticationException(String msg) {
		super(msg);
	}

	public WellsAuthenticationException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
