package br.com.wells.app.presentation.rest.controller.usuario.dto.response;

import java.time.LocalDateTime;
import java.util.Set;

public record UsuarioResponseDto(
        Long id,
        String username,
        Set<String> roles,
        LocalDateTime dataCriacao

) {
}
