package br.com.wells.app.presentation.rest.controller.usuario.dto.request.swagger;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.HashSet;
import java.util.Set;

public interface UsuarioCadastrarDtoSwagger {

    @Schema(description = "username", example = "teste@wellsstore.br")
    String username();

    @Schema(description = "senha", example = "123456", minLength = 6, maxLength = 6)
    String senha();

    @Schema(description = "roles", example = "[\"ADMIN\",\"CLIENTE\"]", minLength = 6, maxLength = 100)
    default Set<String> roles() {
        return new HashSet<>();
    }
}
