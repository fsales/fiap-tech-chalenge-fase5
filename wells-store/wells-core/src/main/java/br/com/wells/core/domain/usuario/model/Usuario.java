package br.com.wells.core.domain.usuario.model;

import br.com.wells.core.domain.usuario.exception.UsuarioInvalidoException;
import br.com.wells.core.util.EmailUtil;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public record Usuario(
        Long id,
        @NonNull String username,
        @NonNull String senha,
        @NonNull Set<Role> roles,
        LocalDateTime dataCriacao,
        LocalDateTime dataAlteracao
) {

    public static Usuario criar(
            final String username,
            final String senha,
            final Set<String> nomesRoles
    ) {
        validarCamposObrigatorios(username, senha, nomesRoles);
        validarEmail(username);

        Set<Role> roles = nomesRoles.stream()
                .map(Role::criar)
                .collect(Collectors.toSet());

        return new Usuario(
                null,
                username,
                SenhaCriptografada.criptografar(senha).valor(),
                roles,
                LocalDateTime.now(),
                null
        );
    }

    private static void validarCamposObrigatorios(
            final String username,
            final String senha,
            final Set<String> nomesRoles
    ) {
        validarNaoVazio(username, "username");
        validarNaoVazio(senha, "senha");
        Objects.requireNonNull(nomesRoles, "A lista de roles não pode ser nula");
    }

    private static void validarNaoVazio(
            final String campo,
            final String nomeCampo
    ) {
        if (campo == null || campo.trim().isEmpty()) {
            throw new UsuarioInvalidoException(String.format("O campo '%s' é obrigatório e não pode consistir apenas em espaços em branco.", nomeCampo));
        }
    }


    private static void validarEmail(
            final String username
    ) {
        if (!EmailUtil.isValidEmail(username)) {
            throw new UsuarioInvalidoException("O campo 'username' não é um e-mail válido");
        }
    }

    /**
     * @param senha
     * @return
     */
    public Usuario alterar(
            final String senha
    ) {
        validarNaoVazio(senha, "senha");

        return new Usuario(
                id,
                username,
                SenhaCriptografada.criptografar(senha).valor(),
                roles,
                dataCriacao,
                dataAlteracao
        );
    }

    public Usuario alterar(
            final Long id,
            final String senha
    ) {
        validarNaoVazio(senha, "senha");

        return new Usuario(
                id,
                username,
                SenhaCriptografada.criptografar(senha).valor(),
                roles,
                dataCriacao,
                dataAlteracao
        );
    }

    /**
     * @param nomesRoles
     * @return
     */
    public Usuario alterar(
            final @NonNull Set<Role> nomesRoles
    ) {
        if (nomesRoles.isEmpty()) {
            throw new UsuarioInvalidoException("A role(s) do 'usuário' é de preenchimento obrigatório");
        }
        return new Usuario(
                id,
                username,
                senha,
                nomesRoles,
                dataCriacao,
                dataAlteracao
        );
    }

}
