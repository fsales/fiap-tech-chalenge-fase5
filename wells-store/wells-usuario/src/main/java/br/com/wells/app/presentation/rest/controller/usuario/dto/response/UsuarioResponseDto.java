package br.com.wells.app.presentation.rest.controller.usuario.dto.response;

import java.time.LocalDateTime;

public record UsuarioResponseDto(
        Long id,
        String username,
        String role,
        LocalDateTime dataCriacao

) {
}
