package br.com.wells.wellsgateway.infrastructure.spring.cloud.gateway.filter.exception.handler;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Getter
@ToString
public class ErrorMessage {

	private final String path;

	private final String method;

	private final int status;

	private final String statusText;

	private final String message;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Map<String, String> errors;

	public ErrorMessage(ServerHttpRequest request, HttpStatus status, String message) {
		this.path = request.getURI().getPath();
		this.method = request.getMethod().toString();
		this.status = status.value();
		this.statusText = status.getReasonPhrase();
		this.message = message;
	}

	public ErrorMessage(ServerHttpRequest request, HttpStatus status, String message, BindingResult result) {
		this.path = request.getURI().getPath();
		this.method = request.getMethod().toString();
		this.status = status.value();
		this.statusText = status.getReasonPhrase();
		this.message = message;
		addErrors(result);
	}

	private void addErrors(BindingResult result) {
		this.errors = new HashMap<>();
		for (FieldError fieldError : result.getFieldErrors()) {
			this.errors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
	}

}
