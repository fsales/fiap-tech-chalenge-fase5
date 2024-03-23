package br.com.wells.wellspagamento.presentation.exception;

import br.com.wells.core.domain.usuario.exception.EntityNotFoundException;
import br.com.wells.core.domain.usuario.exception.SenhaInvalidaException;
import br.com.wells.core.domain.usuario.exception.UsernameUniqueViolationException;
import br.com.wells.core.domain.usuario.exception.UsuarioInvalidoException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

	private static final String MSG_ERROR = "[Error ] - ";

	@ExceptionHandler({ SenhaInvalidaException.class, UsuarioInvalidoException.class, IllegalArgumentException.class })
	public ResponseEntity<ErrorMessage> erroBadRequest(RuntimeException ex, HttpServletRequest request) {
		log.error(MSG_ERROR, ex);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.contentType(MediaType.APPLICATION_JSON)
			.body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, ex.getMessage()));
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorMessage> entityNotFoundException(RuntimeException ex, HttpServletRequest request) {
		log.error(MSG_ERROR, ex);
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
			.contentType(MediaType.APPLICATION_JSON)
			.body(new ErrorMessage(request, HttpStatus.NOT_FOUND, ex.getMessage()));
	}

	@ExceptionHandler(UsernameUniqueViolationException.class)
	public ResponseEntity<ErrorMessage> uniqueViolationException(RuntimeException ex, HttpServletRequest request) {
		log.error(MSG_ERROR, ex);
		return ResponseEntity.status(HttpStatus.CONFLICT)
			.contentType(MediaType.APPLICATION_JSON)
			.body(new ErrorMessage(request, HttpStatus.CONFLICT, ex.getMessage()));
	}

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public ResponseEntity<ErrorMessage> methodArgumentNotValidException(MethodArgumentNotValidException ex,
			HttpServletRequest request, BindingResult result) {
		log.error(MSG_ERROR, ex);
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
			.contentType(MediaType.APPLICATION_JSON)
			.body(new ErrorMessage(request, HttpStatus.UNPROCESSABLE_ENTITY, "Campo(s) invalido(s)", result));
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorMessage> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex,
			HttpServletRequest request) {
		log.error(MSG_ERROR, ex);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.contentType(MediaType.APPLICATION_JSON)
			.body(new ErrorMessage(request, HttpStatus.BAD_REQUEST,
					"Erro de leitura JSON: " + ex.getMostSpecificCause().getMessage()));
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ErrorMessage> unexpectedTypeException(ValidationException ex, HttpServletRequest request) {
		log.error(MSG_ERROR, ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.contentType(MediaType.APPLICATION_JSON)
			.body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()));
	}

}
