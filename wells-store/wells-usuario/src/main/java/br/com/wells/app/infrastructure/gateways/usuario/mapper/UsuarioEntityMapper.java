package br.com.wells.app.infrastructure.gateways.usuario.mapper;

import br.com.wells.app.infrastructure.database.postgres.entity.UsuarioEntity;
import br.com.wells.core.domain.usuario.model.Role;
import br.com.wells.core.domain.usuario.model.Usuario;

import java.util.stream.Collectors;

public final class UsuarioEntityMapper {

	private UsuarioEntityMapper() {
	}

	/**
	 * @param usuario
	 * @return
	 */
	public static UsuarioEntity convertToUsuarioEntity(Usuario usuario) {

		return UsuarioEntity.builder()
			.username(usuario.username())
			.senha(usuario.senha())
			.roles(usuario.roles().stream().map(RolerEntityMapper::convertToRoleEntity).collect(Collectors.toSet()))

			.build();
	}

	/**
	 * @param usuarioEntity
	 * @return
	 */
	public static Usuario convertToUsuario(UsuarioEntity usuarioEntity) {
		return new Usuario(usuarioEntity.getId(), usuarioEntity.getUsername(), usuarioEntity.getSenha(),
				usuarioEntity.getRoles().stream().map(u -> Role.criar(u.getNome())).collect(Collectors.toSet()),
				usuarioEntity.getDataCriacao(), usuarioEntity.getDataModificacao());
	}

}
