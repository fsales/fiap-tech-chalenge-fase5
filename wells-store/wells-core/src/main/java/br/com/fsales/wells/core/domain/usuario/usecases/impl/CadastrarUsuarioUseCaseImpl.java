package br.com.fsales.wells.core.domain.usuario.usecases.impl;

import br.com.fsales.wells.core.domain.usuario.exception.UsernameUniqueViolationException;
import br.com.fsales.wells.core.domain.usuario.gateways.CadastrarUsuarioGateway;
import br.com.fsales.wells.core.domain.usuario.gateways.ConsultarUsuarioPorUsernameGateway;
import br.com.fsales.wells.core.domain.usuario.model.Usuario;
import br.com.fsales.wells.core.domain.usuario.usecases.CadastrarUsuarioUseCase;

public class CadastrarUsuarioUseCaseImpl implements CadastrarUsuarioUseCase {

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
    public Usuario execute(Usuario usuario) {
        validarUserName(usuario);

        return cadastrarUsuarioGateway.execute(usuario);
    }

    private void validarUserName(Usuario usuario) {
        var username = usuario.username();
        if (username.trim().isEmpty())
            return;

        if (consultarUsuarioPorUsername.existsByUsername(username)) {
            throw new UsernameUniqueViolationException("Usuário já cadastrado");
        }
    }
}
