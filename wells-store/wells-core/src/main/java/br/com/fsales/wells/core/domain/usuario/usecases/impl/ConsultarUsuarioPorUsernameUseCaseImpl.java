package br.com.fsales.wells.core.domain.usuario.usecases.impl;

import br.com.fsales.wells.core.domain.usuario.exception.EntityNotFoundException;
import br.com.fsales.wells.core.domain.usuario.gateways.ConsultarUsuarioPorUsernameGateway;
import br.com.fsales.wells.core.domain.usuario.model.Usuario;
import br.com.fsales.wells.core.domain.usuario.usecases.ConsultarUsuarioPorUsernameUseCase;

public class ConsultarUsuarioPorUsernameUseCaseImpl implements ConsultarUsuarioPorUsernameUseCase {

    private final ConsultarUsuarioPorUsernameGateway consultarUsuarioPorUsernameGateway;

    public ConsultarUsuarioPorUsernameUseCaseImpl(
            ConsultarUsuarioPorUsernameGateway consultarUsuarioPorUsernameGateway
    ) {
        this.consultarUsuarioPorUsernameGateway = consultarUsuarioPorUsernameGateway;
    }

    @Override
    public Usuario find(String username) {

        return consultarUsuarioPorUsernameGateway
                .find(
                        username
                ).orElseThrow(
                        () -> new EntityNotFoundException(String.format("Usuario com '%s' não encontrado", username))
                );
    }
}
