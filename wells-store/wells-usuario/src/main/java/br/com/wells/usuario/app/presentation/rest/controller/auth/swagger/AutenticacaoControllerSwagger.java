package br.com.wells.usuario.app.presentation.rest.controller.auth.swagger;

import br.com.wells.usuario.app.presentation.exception.ErrorMessage;
import br.com.wells.usuario.app.presentation.rest.controller.auth.dto.request.UsuarioLoginDto;
import br.com.wells.usuario.app.presentation.rest.controller.auth.dto.response.LoginResponseDTO;
import br.com.wells.usuario.app.presentation.rest.controller.auth.dto.response.TokenResponseDTO;
import br.com.wells.usuario.app.presentation.rest.controller.response.generic.GenericResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

@Tag(name = "Autenticação", description = "Recurso para proceder com a autenticação na API")
public interface AutenticacaoControllerSwagger {

	@Operation(summary = "Autenticar na API", description = "Recurso de autenticação na API",
			responses = {
					@ApiResponse(responseCode = "200",
							description = "Autenticação realizada com sucesso e retorno de um bearer token",
							content = @Content(mediaType = "application/json",
									schema = @Schema(implementation = LoginResponseDTO.class))),
					@ApiResponse(responseCode = "400", description = "Credenciais inválidas",
							content = @Content(mediaType = "application/json",
									schema = @Schema(implementation = ErrorMessage.class))),
					@ApiResponse(responseCode = "422", description = "Campo(s) Inválido(s)",
							content = @Content(mediaType = "application/json",
									schema = @Schema(implementation = ErrorMessage.class))) })
	ResponseEntity<GenericResponse<LoginResponseDTO>> autenticar(final UsuarioLoginDto usuarioLoginDto,
			HttpServletRequest request);

	@Operation(summary = "Obter token", description = "Recurso para obter um token",
			responses = {
					@ApiResponse(responseCode = "200", description = "Token obtido com sucesso",
							content = @Content(mediaType = "application/json",
									schema = @Schema(implementation = LoginResponseDTO.class))),
					@ApiResponse(responseCode = "400", description = "Falha na autenticação",
							content = @Content(mediaType = "application/json",
									schema = @Schema(implementation = ErrorMessage.class))),
					@ApiResponse(responseCode = "422", description = "Campo(s) Inválido(s)",
							content = @Content(mediaType = "application/json",
									schema = @Schema(implementation = ErrorMessage.class))) })
	ResponseEntity<GenericResponse<LoginResponseDTO>> getToken(UsuarioLoginDto usuarioLoginDto,
			HttpServletRequest request);

	@Operation(summary = "Validar token", description = "Recurso para validar um token",
			responses = {
					@ApiResponse(responseCode = "200", description = "Token é válido",
							content = @Content(mediaType = "application/json",
									schema = @Schema(implementation = String.class))),
					@ApiResponse(responseCode = "400", description = "Token inválido",
							content = @Content(mediaType = "application/json",
									schema = @Schema(implementation = ErrorMessage.class))),
					@ApiResponse(responseCode = "422", description = "Campo(s) Inválido(s)",
							content = @Content(mediaType = "application/json",
									schema = @Schema(implementation = ErrorMessage.class))) })
	ResponseEntity<GenericResponse<TokenResponseDTO>> validateToken(
			@Parameter(description = "Token gerado no login", required = true) String token,
			HttpServletRequest request);

}
