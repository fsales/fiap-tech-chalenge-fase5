package br.com.fsales.wells.core.domain.usuario.model;

import static br.com.fsales.wells.core.util.EmailUtil.isValidEmail;

import java.time.LocalDateTime;
import java.util.Objects;

import br.com.fsales.wells.core.domain.usuario.exception.UsuarioInvalidoException;
import lombok.NonNull;

public record Usuario(
        Long id,
        @NonNull String username,
        @NonNull String senha,
        @NonNull Role role,
        LocalDateTime dataCriacao,
        LocalDateTime dataAlteracao
) {

    public static Usuario criar(
            String username,
            String senha,
            Role role
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
            String username,
            String senha,
            Role role
    ) {
        validarNaoVazio(username, "username");
        validarNaoVazio(senha, "senha");
        Objects.requireNonNull(role, "O campo 'role' não pode ser nulo");
    }

    private static void validarNaoVazio(
            String campo,
            String nomeCampo
    ) {
        if (campo == null || campo.trim().isEmpty()) {
            throw new UsuarioInvalidoException(String.format("O campo '%s' é obrigatório e não pode consistir apenas em espaços em branco.", nomeCampo));
        }
    }

    private static void validarEmail(
            String username
    ) {
        if (!isValidEmail(username)) {
            throw new UsuarioInvalidoException("O campo 'username' não é um e-mail válido");
        }
    }

    public Usuario alterar(
            Long id,
            String senha,
            Role role
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


}
