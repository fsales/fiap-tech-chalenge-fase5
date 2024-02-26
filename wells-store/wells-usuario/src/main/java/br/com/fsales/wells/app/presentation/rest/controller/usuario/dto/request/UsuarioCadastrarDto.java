package br.com.fsales.wells.app.presentation.rest.controller.usuario.dto.request;

import br.com.fsales.wells.app.presentation.rest.controller.usuario.dto.request.swagger.UsuarioCadastrarDtoSwagger;
import br.com.fsales.wells.app.presentation.rest.validation.CreateInfo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UsuarioCadastrarDto(
        @NotEmpty(groups = {CreateInfo.class})
        @Email(message = "formato do e-mail está invalido", regexp = "^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$", groups = {CreateInfo.class})
        @Size(min = 10, max = 100, message = "O usuário deve ter no 10 caracteres", groups = {CreateInfo.class})
        String usuario,
        @NotEmpty(groups = {CreateInfo.class})
        @Size(min = 6, max = 6, message = "A senha deve ter no 6 caracteres", groups = {CreateInfo.class})
        String senha
) implements UsuarioCadastrarDtoSwagger {
}