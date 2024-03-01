package br.com.fsales.wells.core.domain.usuario.usecases;

import br.com.fsales.wells.core.domain.usuario.model.Usuario;

public interface ConsultarUsuarioPorUsernameUseCase {
    Usuario find(
            final String username
    );
}
