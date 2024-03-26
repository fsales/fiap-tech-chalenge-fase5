package br.com.wells.wellsgateway.infrastructure.spring.cloud.gateway.filter.exception.handler;

import br.com.wells.wellsgateway.infrastructure.spring.cloud.gateway.filter.exception.MissingAuthorizationHeaderException;
import br.com.wells.wellsgateway.infrastructure.spring.cloud.gateway.filter.exception.TokenValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {

	private static final String MSG_ERROR = "[Error ] - ";

	@ExceptionHandler({ MissingAuthorizationHeaderException.class })
	public ResponseEntity<ErrorMessage> errorUnathorized(RuntimeException ex, ServerHttpRequest request) {
		log.error(MSG_ERROR, ex);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
			.contentType(MediaType.APPLICATION_JSON)
			.body(new ErrorMessage(request, HttpStatus.UNAUTHORIZED, ex.getMessage()));
	}

	@ExceptionHandler({ TokenValidationException.class })
	public ResponseEntity<ErrorMessage> erroForbidden(RuntimeException ex, ServerHttpRequest request) {
		log.error(MSG_ERROR, ex);
		return ResponseEntity.status(HttpStatus.FORBIDDEN)
			.contentType(MediaType.APPLICATION_JSON)
			.body(new ErrorMessage(request, HttpStatus.FORBIDDEN, ex.getMessage()));
	}

}
