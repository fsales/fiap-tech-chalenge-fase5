package br.com.wells.core.domain.usuario.model;

import br.com.wells.core.domain.usuario.exception.UsuarioInvalidoException;
import br.com.wells.core.util.EmailUtil;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.Objects;

public record Usuario(
        Long id,
        @NonNull String username,
        @NonNull String senha,
        @NonNull Role role,
        LocalDateTime dataCriacao,
        LocalDateTime dataAlteracao
) {

    public static Usuario criar(
            final String username,
            final String senha,
            final Role role
    ) {
        validarCamposObrigatorios(username, senha, role);
        validarEmail(username);

        return new Usuario(
                null,
                username,
                SenhaCriptografada.criptografar(senha).valor(),
                role,
                LocalDateTime.now(),
                null
        );
    }

    private static void validarCamposObrigatorios(
            final String username,
            final String senha,
            final Role role
    ) {
        validarNaoVazio(username, "username");
        validarNaoVazio(senha, "senha");
        Objects.requireNonNull(role, "O campo 'role' não pode ser nulo");
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

    public Usuario alterar(
            final Long id,
            final String senha,
            final Role role
    ) {
        Objects.requireNonNull(id, "O campo 'id' não pode ser nulo");

        validarCamposObrigatorios(username, senha, role);
        validarEmail(username);

        return new Usuario(
                id,
                username,
                SenhaCriptografada.criptografar(senha).valor(),
                role,
                dataCriacao,
                LocalDateTime.now()
        );
    }

    /**
     * @param senha
     * @return
     */
    public Usuario alterar(
            final String senha
    ) {

        return alterar(
                id,
                senha,
                role
        );
    }


}
