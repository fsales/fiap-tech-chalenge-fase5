package br.com.wells.usuario.app.presentation.rest.controller.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objeto de resposta para autenticação de login. Contém o token gerado após um login bem-sucedido.")
public interface LoginResponseDTOSwagger {

	@Schema(description = "O token JWT gerado após um login bem-sucedido.")
	String token();

}
