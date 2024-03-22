package br.com.wells.usuario.app.presentation.rest.controller.usuario.dto.response;

import java.time.LocalDateTime;
import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objeto de resposta para informações do usuário.")
public interface UsuarioResponseDtoSwagger {

	@Schema(description = "O ID do usuário.")
	Long id();

	@Schema(description = "O nome de usuário.")
	String username();

	@Schema(description = "Os papéis do usuário.")
	Set<String> roles();

	@Schema(description = "A data de criação do usuário.")
	LocalDateTime dataCriacao();

}