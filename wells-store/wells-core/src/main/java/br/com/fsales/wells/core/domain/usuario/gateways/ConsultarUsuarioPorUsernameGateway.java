package br.com.fsales.wells.core.domain.usuario.gateways;

import br.com.fsales.wells.core.domain.usuario.model.Usuario;

public interface ConsultarUsuarioPorUsernameGateway {
    Usuario find(String username);

    boolean existsByUsername(String username);
}
