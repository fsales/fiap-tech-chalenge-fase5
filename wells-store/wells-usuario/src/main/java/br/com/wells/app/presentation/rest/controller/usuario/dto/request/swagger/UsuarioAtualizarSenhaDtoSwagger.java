package br.com.wells.app.presentation.rest.controller.usuario.dto.request.swagger;

import io.swagger.v3.oas.annotations.media.Schema;

public interface UsuarioAtualizarSenhaDtoSwagger {

	@Schema(description = "senhaAtual", example = "123456", minLength = 6, maxLength = 6)
	String senhaAtual();

	@Schema(description = "novaSenha", example = "789101112", minLength = 6, maxLength = 6)
	String novaSenha();

	@Schema(description = "confirmaSenha", example = "789101112", minLength = 6, maxLength = 6)
	String confirmaSenha();

}
