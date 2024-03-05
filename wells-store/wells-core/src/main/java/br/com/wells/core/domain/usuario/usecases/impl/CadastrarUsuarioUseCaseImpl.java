package br.com.wells.core.domain.usuario.usecases.impl;

import br.com.wells.core.domain.usuario.exception.UsernameUniqueViolationException;
import br.com.wells.core.domain.usuario.exception.UsuarioInvalidoException;
import br.com.wells.core.domain.usuario.gateways.CadastrarUsuarioGateway;
import br.com.wells.core.domain.usuario.gateways.ConsultarRolePorNomeGateway;
import br.com.wells.core.domain.usuario.gateways.ConsultarUsuarioPorUsernameGateway;
import br.com.wells.core.domain.usuario.model.Role;
import br.com.wells.core.domain.usuario.model.Usuario;
import br.com.wells.core.domain.usuario.usecases.CadastrarUsuarioUseCase;

import java.util.stream.Collectors;

public final class CadastrarUsuarioUseCaseImpl implements CadastrarUsuarioUseCase {

    private final CadastrarUsuarioGateway cadastrarUsuarioGateway;
    private final ConsultarUsuarioPorUsernameGateway consultarUsuarioPorUsername;

    private final ConsultarRolePorNomeGateway consultarRolePorNomeGateway;

    public CadastrarUsuarioUseCaseImpl(
            CadastrarUsuarioGateway cadastrarUsuarioGateway,
            ConsultarUsuarioPorUsernameGateway consultarUsuarioPorUsername,
            ConsultarRolePorNomeGateway consultarRolePorNomeGateway
    ) {
        this.cadastrarUsuarioGateway = cadastrarUsuarioGateway;
        this.consultarUsuarioPorUsername = consultarUsuarioPorUsername;
        this.consultarRolePorNomeGateway = consultarRolePorNomeGateway;
    }

    @Override
    public Usuario execute(
            final Usuario usuario
    ) {
        validarUserName(usuario);

        var roles = consultarRolePorNomeGateway
                .find(
                        usuario.roles().stream().map(Role::nome).collect(Collectors.toSet())
                ).orElseThrow(
                        () -> new UsuarioInvalidoException("Roles não encontrada")
                );

        var usuarioSalvar = usuario.alterar(roles);
        return cadastrarUsuarioGateway.execute(usuarioSalvar);
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
