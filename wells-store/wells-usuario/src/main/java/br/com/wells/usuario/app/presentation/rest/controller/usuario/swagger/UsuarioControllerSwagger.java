package br.com.wells.usuario.app.presentation.rest.controller.usuario.swagger;

import br.com.wells.usuario.app.presentation.exception.ErrorMessage;
import br.com.wells.usuario.app.presentation.rest.controller.response.generic.GenericResponse;
import br.com.wells.usuario.app.presentation.rest.controller.usuario.dto.request.UsuarioAtualizarSenhaDto;
import br.com.wells.usuario.app.presentation.rest.controller.usuario.dto.request.UsuarioCadastrarDto;
import br.com.wells.usuario.app.presentation.rest.controller.usuario.dto.response.UsuarioResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

@Tag(name = "Usuarios", description = "Recurso para Cadastro de um usuário.")
public interface UsuarioControllerSwagger {

	@Operation(summary = "Criar um novo usuário", description = "Recurso para criar um novo usuário.",
			responses = { @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso",
					content = @Content(mediaType = "application/json",
							schema = @Schema(implementation = UsuarioResponseDto.class))) })
	ResponseEntity<GenericResponse<UsuarioResponseDto>> cadastrar(UsuarioCadastrarDto cadastrarDto,
			UriComponentsBuilder uriComponentsBuilder);

	@Operation(summary = "Recuperar um usuário pelo id",
			description = "Requisição exige um Bearer Token. Acesso restrito a ADMIN|CLIENTE",
			security = @SecurityRequirement(name = "security"),
			responses = {
					@ApiResponse(responseCode = "200", description = "Recurso recuperado com sucesso",
							content = @Content(mediaType = "application/json",
									schema = @Schema(implementation = UsuarioResponseDto.class))),
					@ApiResponse(responseCode = "403", description = "Usuário sem permissão para acessar este recurso",
							content = @Content(mediaType = "application/json",
									schema = @Schema(implementation = ErrorMessage.class))),
					@ApiResponse(responseCode = "404", description = "Recurso não encontrado",
							content = @Content(mediaType = "application/json",
									schema = @Schema(implementation = ErrorMessage.class))) })
	ResponseEntity<GenericResponse<UsuarioResponseDto>> consultarUsarioPorId(Long id);

	@Operation(summary = "Atualizar a senha",
			description = "Requisição exige um Bearer Token. Acesso restrito a ADMIN|CLIENTE",
			security = @SecurityRequirement(name = "security"),
			responses = { @ApiResponse(responseCode = "204", description = "Senha atualizada com sucesso"),
					@ApiResponse(responseCode = "400", description = "Senha não confere",
							content = @Content(mediaType = "application/json",
									schema = @Schema(implementation = ErrorMessage.class))),
					@ApiResponse(responseCode = "403", description = "Usuário sem permissão para acessar este recurso",
							content = @Content(mediaType = "application/json",
									schema = @Schema(implementation = ErrorMessage.class))),
					@ApiResponse(responseCode = "422", description = "Campos invalidos ou mal formatados",
							content = @Content(mediaType = "application/json",
									schema = @Schema(implementation = ErrorMessage.class))) })
	ResponseEntity<GenericResponse<Void>> alterarSenha(Long id, UsuarioAtualizarSenhaDto atualizarSenhaDto);

	@Operation(summary = "Listar todos os usuários cadastrados",
			description = "Requisição exige um Bearer Token. Acesso restrito a ADMIN",
			security = @SecurityRequirement(name = "security"),
			responses = {
					@ApiResponse(responseCode = "200", description = "Lista com todos os usuários cadastrados",
							content = @Content(mediaType = "application/json",
									array = @ArraySchema(schema = @Schema(implementation = UsuarioResponseDto.class)))),
					@ApiResponse(responseCode = "403", description = "Usuário sem permissão para acessar este recurso",
							content = @Content(mediaType = "application/json",
									schema = @Schema(implementation = ErrorMessage.class))) })
	@PageableAsQueryParam
	ResponseEntity<GenericResponse<Page<UsuarioResponseDto>>> listarTodos(@Parameter(hidden = true) Pageable pageable);

}
