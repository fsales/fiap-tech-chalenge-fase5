package br.com.fsales.wells.app.infrastructure.gateways.usuario;

import br.com.fsales.wells.app.infrastructure.database.postgres.repository.UsuarioEntityRepository;
import br.com.fsales.wells.app.infrastructure.gateways.usuario.mapper.UsuarioEntityMapper;
import br.com.fsales.wells.core.domain.usuario.exception.EntityNotFoundException;
import br.com.fsales.wells.core.domain.usuario.gateways.ConsultarUsuarioPorUsernameGateway;
import br.com.fsales.wells.core.domain.usuario.model.Usuario;

public class ConsultarUsuarioPorUsernameGatewayImpl implements ConsultarUsuarioPorUsernameGateway {

    private final UsuarioEntityRepository usuarioEntityRepository;

    public ConsultarUsuarioPorUsernameGatewayImpl(UsuarioEntityRepository usuarioEntityRepository) {
        this.usuarioEntityRepository = usuarioEntityRepository;
    }

    @Override
    public Usuario find(String username) {
        return usuarioEntityRepository.findByUsername(username)
                .map(UsuarioEntityMapper::convertToUsuario)
                .orElseThrow(
                        () -> new EntityNotFoundException(String.format("Usuario com '%s' não encontrado", username))
                );
    }

    @Override
    public boolean existsByUsername(String username) {
        return usuarioEntityRepository.existsByUsernameIgnoreCase(username);
    }
}
