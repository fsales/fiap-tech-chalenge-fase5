package br.com.fsales.wells.app.presentation.rest.controller.usuario.swagger;

import br.com.fsales.wells.app.presentation.exception.ErrorMessage;
import br.com.fsales.wells.app.presentation.rest.controller.usuario.dto.request.UsuarioCadastrarDto;
import br.com.fsales.wells.app.presentation.rest.controller.usuario.dto.response.UsuarioResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Usuarios", description = "Recurso para Cadastro de um usuário.")
public interface UsuarioControllerSwagger {

    @Operation(summary = "Criar um novo usuário", description = "Recurso para criar um novo usuário.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDto.class)))
            }
    )
    ResponseEntity<UsuarioResponseDto> cadastrar(
            UsuarioCadastrarDto cadastrarDto
    );

    @Operation(summary = "Recuperar um usuário pelo id", description = "Requisição exige um Bearer Token. Acesso restrito a ADMIN|CLIENTE",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso recuperado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDto.class))),
                    @ApiResponse(responseCode = "403", description = "Usuário sem permissão para acessar este recurso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    ResponseEntity<UsuarioResponseDto> consultarUsarioPorId(
            Long id
    );
}
