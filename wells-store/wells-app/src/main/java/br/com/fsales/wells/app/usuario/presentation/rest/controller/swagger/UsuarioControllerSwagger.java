package br.com.fsales.wells.app.usuario.presentation.rest.controller.swagger;

import br.com.fsales.wells.app.usuario.presentation.rest.controller.dto.UsuarioCadastrarDto;
import br.com.fsales.wells.app.usuario.presentation.rest.controller.dto.UsuarioResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Usuarios", description = "Recurso para Cadastro de um usuário.")
public interface UsuarioControllerSwagger {

    @Operation(summary = "Criar um novo usuário", description = "Recurso para criar um novo usuário.")
    ResponseEntity<UsuarioResponseDto> cadastrar(
            UsuarioCadastrarDto cadastrarDto
    );
}
