package br.com.wells.usuario.app.infrastructure.database.postgres.entity.listener;

import br.com.wells.usuario.app.infrastructure.database.postgres.entity.UsuarioEntity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.util.Locale;

public final class UsuarioEntityListener {

	@PrePersist
	public void beforePersist(UsuarioEntity usuario) {
		transformarString(usuario);
	}

	@PreUpdate
	public void beforeUpdate(UsuarioEntity usuario) {
		transformarString(usuario);
	}

	private void transformarString(UsuarioEntity usuario) {
		// Converte as strings para maiúsculas e remove espaços em branco
		usuario.setUsername(usuario.getUsername().toLowerCase(Locale.ROOT).trim());
	}

}
