package br.com.wells.core.domain.usuario.usecases;

import br.com.wells.core.domain.usuario.model.Usuario;

public interface ConsultarUsuarioPorUsernameUseCase {
    Usuario find(
            final String username
    );
}
