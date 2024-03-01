package br.com.wells.app.presentation.rest.controller.auth.dto.request;

import br.com.wells.app.presentation.rest.controller.usuario.dto.request.swagger.UsuarioCadastrarDtoSwagger;
import br.com.wells.app.presentation.rest.validation.CreateInfo;
import br.com.wells.app.presentation.rest.validation.FindInfo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UsuarioLoginDto (
        @NotEmpty(groups = {FindInfo.class})
        @Email(message = "formato do e-mail está invalido", regexp = "^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$", groups = {CreateInfo.class})
        @Size(min = 10, max = 100, message = "O usuário deve ter no 10 caracteres", groups = {CreateInfo.class})
        String username,
        @NotEmpty(groups = {FindInfo.class})
        @Size(min = 6, max = 6, message = "A senha deve ter 6 caracteres", groups = {CreateInfo.class})
        String senha
) implements UsuarioCadastrarDtoSwagger {
}