package br.com.wells.app.infrastructure.gateways.usuario.mapper;

import br.com.wells.app.infrastructure.database.postgres.entity.UsuarioEntity;
import br.com.wells.core.domain.usuario.model.Role;
import br.com.wells.core.domain.usuario.model.Usuario;

public final class UsuarioEntityMapper {

    private UsuarioEntityMapper() {
    }

    /**
     * @param usuario
     * @return
     */
    public static UsuarioEntity convertToUsuarioEntity(
            Usuario usuario
    ) {
        return UsuarioEntity.builder()
                .username(usuario.username())
                .senha(usuario.senha())
                .role(
                        UsuarioEntity
                                .Role
                                .valueOf(
                                        usuario
                                                .role()
                                                .name()
                                )
                )
                .build();
    }

    /**
     * @param usuarioEntity
     * @return
     */
    public static Usuario convertToUsuario(
            UsuarioEntity usuarioEntity
    ) {
        return new Usuario(
                usuarioEntity.getId(),
                usuarioEntity.getUsername(),
                usuarioEntity.getSenha(),
                Role.valueOf(
                        usuarioEntity
                                .getRole()
                                .name()
                ),
                usuarioEntity.getDataCriacao(),
                usuarioEntity.getDataModificacao()
        );
    }
}