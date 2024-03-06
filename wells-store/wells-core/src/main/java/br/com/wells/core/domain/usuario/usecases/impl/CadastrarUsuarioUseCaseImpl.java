package br.com.wells.core.domain.usuario.usecases.impl;

import java.util.Set;
import java.util.stream.Collectors;

import br.com.wells.core.domain.usuario.exception.UsernameUniqueViolationException;
import br.com.wells.core.domain.usuario.exception.UsuarioInvalidoException;
import br.com.wells.core.domain.usuario.gateways.CadastrarUsuarioGateway;
import br.com.wells.core.domain.usuario.gateways.ConsultarRolePorNomeGateway;
import br.com.wells.core.domain.usuario.gateways.ConsultarUsuarioPorUsernameGateway;
import br.com.wells.core.domain.usuario.model.Role;
import br.com.wells.core.domain.usuario.model.Usuario;
import br.com.wells.core.domain.usuario.usecases.CadastrarUsuarioUseCase;

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
    public Usuario execute(final Usuario usuario) {
        validarUserName(usuario);

        Set<String> nomesRoles = usuario.roles().stream().map(Role::nome).collect(Collectors.toSet());

        Set<Role> rolesEncontradas = consultarRolePorNomeGateway.find(
                nomesRoles
        )
        .filter(lista -> !lista.isEmpty())
        .orElseThrow(
                () -> criarExcecaoRolesNaoEncontradas(nomesRoles)
        );

        return cadastrarUsuarioGateway.execute(usuario.alterar(rolesEncontradas));
    }

    private UsuarioInvalidoException criarExcecaoRolesNaoEncontradas(Set<String> nomesRolesNaoEncontradas) {
        String mensagem = "Roles não encontrada(s): " +
                          String.join(", ", nomesRolesNaoEncontradas);
        return new UsuarioInvalidoException(mensagem);
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
