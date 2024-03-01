package br.com.wells.app.presentation.rest.controller.usuario.dto.mapper;

import br.com.wells.app.presentation.rest.controller.usuario.dto.request.UsuarioCadastrarDto;
import br.com.wells.app.presentation.rest.controller.usuario.dto.response.UsuarioResponseDto;
import br.com.wells.core.domain.usuario.model.Role;
import br.com.wells.core.domain.usuario.model.Usuario;

public final class UsuarioDtoMapper {

    private UsuarioDtoMapper() {
    }


    /**
     * @param usuarioDto
     * @return
     */
    public static Usuario convertToUsuario(
            UsuarioCadastrarDto usuarioDto
    ) {
        return Usuario.criar(
                usuarioDto.username(),
                usuarioDto.senha(),
                Role.ROLE_CLIENTE
        );
    }

    public static UsuarioResponseDto convertToUsuarioResponseDto(
            Usuario usuario
    ) {
        return new UsuarioResponseDto(
                usuario.id(),
                usuario.username(),
                usuario.role().name(),
                usuario.dataCriacao()
        );
    }
}
