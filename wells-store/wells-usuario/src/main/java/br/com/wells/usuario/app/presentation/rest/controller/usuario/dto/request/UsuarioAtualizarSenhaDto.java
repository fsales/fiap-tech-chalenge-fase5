package br.com.wells.usuario.app.presentation.rest.controller.usuario.dto.request;

import br.com.wells.usuario.app.presentation.rest.controller.usuario.dto.request.swagger.UsuarioAtualizarSenhaDtoSwagger;
import br.com.wells.usuario.app.presentation.rest.validation.UpdateInfo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioAtualizarSenhaDto(
		@NotBlank @Size(min = 6, max = 6, message = "A senha deve ter 6 caracteres", groups = {
				UpdateInfo.class }) String senhaAtual,
		@NotBlank @Size(min = 6, max = 6, message = "A senha deve ter 6 caracteres",
				groups = { UpdateInfo.class }) String novaSenha,
		@NotBlank @Size(min = 6, max = 6, message = "A senha deve ter 6 caracteres",
				groups = { UpdateInfo.class }) String confirmaSenha)
		implements
			UsuarioAtualizarSenhaDtoSwagger{
}
