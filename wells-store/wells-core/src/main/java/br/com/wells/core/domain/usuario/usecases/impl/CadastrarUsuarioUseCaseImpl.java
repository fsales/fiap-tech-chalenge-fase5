package br.com.wells.core.domain.usuario.usecases.impl;

import br.com.wells.core.domain.usuario.exception.UsernameUniqueViolationException;
import br.com.wells.core.domain.usuario.gateways.CadastrarUsuarioGateway;
import br.com.wells.core.domain.usuario.gateways.ConsultarUsuarioPorUsernameGateway;
import br.com.wells.core.domain.usuario.model.Usuario;
import br.com.wells.core.domain.usuario.usecases.CadastrarUsuarioUseCase;

public final class CadastrarUsuarioUseCaseImpl implements CadastrarUsuarioUseCase {

    private final CadastrarUsuarioGateway cadastrarUsuarioGateway;
    private final ConsultarUsuarioPorUsernameGateway consultarUsuarioPorUsername;

    public CadastrarUsuarioUseCaseImpl(
            CadastrarUsuarioGateway cadastrarUsuarioGateway,
            ConsultarUsuarioPorUsernameGateway consultarUsuarioPorUsername
    ) {
        this.cadastrarUsuarioGateway = cadastrarUsuarioGateway;
        this.consultarUsuarioPorUsername = consultarUsuarioPorUsername;
    }

    @Override
    public Usuario execute(
            final Usuario usuario
    ) {
        validarUserName(usuario);

        return cadastrarUsuarioGateway.execute(usuario);
    }

    private void validarUserName(
            final Usuario usuario
    ) {
        var username = usuario.username();
        if (username.trim().isEmpty())
            return;

        if (consultarUsuarioPorUsername.existsByUsername(username)) {
            throw new UsernameUniqueViolationException("Usuário já cadastrado");
        }
    }
}
