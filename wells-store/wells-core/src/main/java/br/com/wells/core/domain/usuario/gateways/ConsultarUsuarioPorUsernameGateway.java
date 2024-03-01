package br.com.wells.core.domain.usuario.gateways;

import br.com.wells.core.domain.usuario.model.Usuario;

import java.util.Optional;

public interface ConsultarUsuarioPorUsernameGateway {
    Optional<Usuario> find(String username);

    boolean existsByUsername(String username);
}
