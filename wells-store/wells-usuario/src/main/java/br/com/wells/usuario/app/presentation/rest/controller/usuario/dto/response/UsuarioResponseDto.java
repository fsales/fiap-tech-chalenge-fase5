package br.com.wells.usuario.app.presentation.rest.controller.usuario.dto.response;

import java.time.LocalDateTime;
import java.util.Set;

public record UsuarioResponseDto(Long id, String username, Set<String> roles, LocalDateTime dataCriacao

) implements UsuarioResponseDtoSwagger {
}
