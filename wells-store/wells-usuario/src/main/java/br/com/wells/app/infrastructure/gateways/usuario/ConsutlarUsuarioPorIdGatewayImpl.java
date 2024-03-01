package br.com.wells.app.infrastructure.gateways.usuario;

import br.com.wells.app.infrastructure.database.postgres.repository.UsuarioEntityRepository;
import br.com.wells.app.infrastructure.gateways.usuario.mapper.UsuarioEntityMapper;
import br.com.wells.core.domain.usuario.gateways.ConsutlarUsuarioPorIdGateway;
import br.com.wells.core.domain.usuario.model.Usuario;

import java.util.Optional;

public class ConsutlarUsuarioPorIdGatewayImpl implements ConsutlarUsuarioPorIdGateway {

    private final UsuarioEntityRepository usuarioEntityRepository;

    public ConsutlarUsuarioPorIdGatewayImpl(UsuarioEntityRepository usuarioEntityRepository) {
        this.usuarioEntityRepository = usuarioEntityRepository;
    }

    @Override
    public Optional<Usuario> find(Long id) {

        return usuarioEntityRepository
                .findById(
                        id
                ).map(
                        UsuarioEntityMapper::convertToUsuario
                );
    }
}
