package br.com.fsales.wells.app.presentation.rest.controller.usuario.dto.response;

import java.time.LocalDateTime;

public record UsuarioResponseDto(
        Long id,
        String usuario,
        String role,
        LocalDateTime dataCriacao

) {
}
