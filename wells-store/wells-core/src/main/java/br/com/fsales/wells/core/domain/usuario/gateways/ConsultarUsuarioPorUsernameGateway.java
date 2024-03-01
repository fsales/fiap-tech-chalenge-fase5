package br.com.fsales.wells.core.domain.usuario.gateways;

import java.util.Optional;

import br.com.fsales.wells.core.domain.usuario.model.Usuario;

public interface ConsultarUsuarioPorUsernameGateway {
    Optional<Usuario> find(String username);

    boolean existsByUsername(String username);
}