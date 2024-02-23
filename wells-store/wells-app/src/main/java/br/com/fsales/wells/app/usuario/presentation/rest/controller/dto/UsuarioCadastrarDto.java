package br.com.fsales.wells.app.usuario.presentation.rest.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UsuarioCadastrarDto(
        @NotEmpty
        @Email(message = "formato do e-mail está invalido", regexp = "^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")
        String usuario,
        @NotEmpty
        @Size(min = 6, max = 6, message = "A senha deve ter no 6 caracteres")
        String senha
) {
}
