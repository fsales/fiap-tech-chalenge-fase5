package br.com.fsales.wells.core.domain.usuario.usecases;

import br.com.fsales.wells.core.domain.usuario.model.Usuario;

public interface ConsutlarUsuarioPorIdUseCase {

    /**
     * @param id
     * @return
     */
    Usuario find(
            final Long id
    );
}
