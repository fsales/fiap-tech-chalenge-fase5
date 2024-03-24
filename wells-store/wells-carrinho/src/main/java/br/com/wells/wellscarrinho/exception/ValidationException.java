// ValidationException.java
package br.com.wells.wellscarrinho.exception;

public class ValidationException extends RuntimeException {

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

}