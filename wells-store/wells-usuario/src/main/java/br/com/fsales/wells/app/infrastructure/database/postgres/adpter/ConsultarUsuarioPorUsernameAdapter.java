package br.com.fsales.wells.app.infrastructure.database.postgres.adpter;

import br.com.fsales.wells.app.infrastructure.database.postgres.mapper.UsuarioEntityMapper;
import br.com.fsales.wells.app.infrastructure.database.postgres.repository.UsuarioEntityRepository;
import br.com.fsales.wells.core.domain.usuario.exception.EntityNotFoundException;
import br.com.fsales.wells.core.domain.usuario.model.Usuario;
import br.com.fsales.wells.core.domain.usuario.repository.ConsultarUsuarioPorUsernameRepository;

public class ConsultarUsuarioPorUsernameAdapter implements ConsultarUsuarioPorUsernameRepository {

    private final UsuarioEntityRepository usuarioEntityRepository;

    public ConsultarUsuarioPorUsernameAdapter(UsuarioEntityRepository usuarioEntityRepository) {
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
