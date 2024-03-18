package br.com.wells.app.presentation.rest.controller.response.generic;

import br.com.wells.app.presentation.exception.ErrorMessage;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record GenericResponse<T>(HttpStatus status, T data, ErrorMessage errorMessage) {

	public static <T> GenericResponse<T> success(HttpStatus status, T data) {
		return new GenericResponse<>(status, data, null);
	}

	public static <T> GenericResponse<T> error(HttpStatus status, ErrorMessage errorMessage) {
		return new GenericResponse<>(status, null, errorMessage);
	}
}
