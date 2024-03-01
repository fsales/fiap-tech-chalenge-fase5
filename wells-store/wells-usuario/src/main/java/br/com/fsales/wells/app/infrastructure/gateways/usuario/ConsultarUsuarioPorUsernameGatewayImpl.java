package br.com.fsales.wells.app.infrastructure.gateways.usuario;

import java.util.Optional;

import br.com.fsales.wells.app.infrastructure.database.postgres.repository.UsuarioEntityRepository;
import br.com.fsales.wells.app.infrastructure.gateways.usuario.mapper.UsuarioEntityMapper;
import br.com.fsales.wells.core.domain.usuario.gateways.ConsultarUsuarioPorUsernameGateway;
import br.com.fsales.wells.core.domain.usuario.model.Usuario;

public class ConsultarUsuarioPorUsernameGatewayImpl implements ConsultarUsuarioPorUsernameGateway {

    private final UsuarioEntityRepository usuarioEntityRepository;

    public ConsultarUsuarioPorUsernameGatewayImpl(
            UsuarioEntityRepository usuarioEntityRepository
    ) {
        this.usuarioEntityRepository = usuarioEntityRepository;
    }

    @Override
    public Optional<Usuario> find(String username) {
        return usuarioEntityRepository.findByUsername(username)
                .map(UsuarioEntityMapper::convertToUsuario);
    }

    @Override
    public boolean existsByUsername(String username) {
        return usuarioEntityRepository.existsByUsernameIgnoreCase(username);
    }
}
