package br.com.wells.usuario.app.infrastructure.gateways.usuario.mapper;

import br.com.wells.usuario.app.infrastructure.database.postgres.entity.RoleEntity;
import br.com.wells.core.domain.usuario.model.Role;

public final class RolerEntityMapper {

	private RolerEntityMapper() {
	}

	/**
	 * @param roleEntity
	 * @return
	 */
	public static Role convertToRole(RoleEntity roleEntity) {
		var role = Role.criar(roleEntity.getNome());
		role.id(roleEntity.getId());
		return role;
	}

	/**
	 * @param role
	 * @return
	 */
	public static RoleEntity convertToRoleEntity(Role role) {
		return RoleEntity.builder().nome(role.nome()).id(role.id()).build();
	}

}
