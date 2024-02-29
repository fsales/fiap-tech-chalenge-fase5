package br.com.fsales.wells.core.domain.usuario.repository;

import br.com.fsales.wells.core.domain.usuario.model.Usuario;

public interface ConsultarUsuarioPorUsernameRepository {
    Usuario find(String username);

    boolean existsByUsername(String username);
}
