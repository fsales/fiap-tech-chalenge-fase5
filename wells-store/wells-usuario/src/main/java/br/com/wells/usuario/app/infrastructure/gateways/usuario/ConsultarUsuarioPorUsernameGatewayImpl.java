package br.com.wells.usuario.app.infrastructure.gateways.usuario;

import br.com.wells.usuario.app.infrastructure.database.postgres.repository.UsuarioEntityRepository;
import br.com.wells.usuario.app.infrastructure.gateways.usuario.mapper.UsuarioEntityMapper;
import br.com.wells.core.domain.usuario.gateways.ConsultarUsuarioPorUsernameGateway;
import br.com.wells.core.domain.usuario.model.Usuario;

import java.util.Optional;

public class ConsultarUsuarioPorUsernameGatewayImpl implements ConsultarUsuarioPorUsernameGateway {

	private final UsuarioEntityRepository usuarioEntityRepository;

	public ConsultarUsuarioPorUsernameGatewayImpl(UsuarioEntityRepository usuarioEntityRepository) {
		this.usuarioEntityRepository = usuarioEntityRepository;
	}

	@Override
	public Optional<Usuario> find(String username) {
		return usuarioEntityRepository.findByUsername(username).map(UsuarioEntityMapper::convertToUsuario);
	}

	@Override
	public boolean existsByUsername(String username) {
		return usuarioEntityRepository.existsByUsernameIgnoreCase(username);
	}

}
