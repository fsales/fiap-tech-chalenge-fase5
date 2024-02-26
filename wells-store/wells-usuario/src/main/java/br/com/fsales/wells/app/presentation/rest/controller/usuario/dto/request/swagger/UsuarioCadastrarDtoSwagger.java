package br.com.fsales.wells.app.presentation.rest.controller.usuario.dto.request.swagger;

import io.swagger.v3.oas.annotations.media.Schema;

public interface UsuarioCadastrarDtoSwagger {

    @Schema(description = "usuario", example = "teste@teste.com")
    String usuario();

    @Schema(description = "senha", example = "123456", minLength = 6, maxLength = 6)
    String senha();
}
