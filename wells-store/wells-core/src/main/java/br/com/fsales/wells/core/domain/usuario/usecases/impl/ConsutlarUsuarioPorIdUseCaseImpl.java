package br.com.fsales.wells.core.domain.usuario.usecases.impl;

import br.com.fsales.wells.core.domain.usuario.exception.EntityNotFoundException;
import br.com.fsales.wells.core.domain.usuario.gateways.ConsutlarUsuarioPorIdGateway;
import br.com.fsales.wells.core.domain.usuario.model.Usuario;
import br.com.fsales.wells.core.domain.usuario.usecases.ConsutlarUsuarioPorIdUseCase;

public final class ConsutlarUsuarioPorIdUseCaseImpl implements ConsutlarUsuarioPorIdUseCase {

    private final ConsutlarUsuarioPorIdGateway consutlarUsuarioPorIdGateway;

    public ConsutlarUsuarioPorIdUseCaseImpl(ConsutlarUsuarioPorIdGateway consutlarUsuarioPorIdGateway) {
        this.consutlarUsuarioPorIdGateway = consutlarUsuarioPorIdGateway;
    }


    @Override
    public Usuario find(
            final Long id
    ) {
        return consutlarUsuarioPorIdGateway.find(
                id
        ).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("Usuário id=%s não encontrado",
                                id
                        )
                )
        );
    }
}
