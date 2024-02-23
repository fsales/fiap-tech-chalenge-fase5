package br.com.fsales.wells.app.usuario.presentation.rest.controller.swagger;

import br.com.fsales.wells.app.usuario.presentation.rest.controller.dto.UsuarioCadastrarDto;
import br.com.fsales.wells.app.usuario.presentation.rest.controller.dto.UsuarioResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Usuarios", description = "Recurso para Cadastro de um usuário.")
public interface UsuarioControllerSwagger {

    @Operation(summary = "Criar um novo usuário", description = "Recurso para criar um novo usuário.", responses = {
            @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDto.class)))
    })
    ResponseEntity<UsuarioResponseDto> cadastrar(
            UsuarioCadastrarDto cadastrarDto
    );
}
