package br.com.fsales.wells.core.domain.usuario.model;

import br.com.fsales.wells.core.domain.usuario.exception.UsuarioInvalidoException;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.Objects;

import static br.com.fsales.wells.core.util.EmailUtil.isValidEmail;

public record Usuario(
        Long id,
        @NonNull String usuario,
        @NonNull String senha,
        @NonNull Role role,
        LocalDateTime dataCriacao,
        LocalDateTime dataAlteracao
) {

    public static Usuario criar(
            String usuario,
            String senha,
            Role role
    ) {
        validarCamposObrigatorios(usuario, senha, role);
        validarEmail(usuario);

        return new Usuario(
                null,
                usuario,
                SenhaCriptografada.criptografar(senha).valor(),
                role,
                LocalDateTime.now(),
                null
        );
    }

    public Usuario alterar(
            Long id,
            String senha,
            Role role
    ) {
        Objects.requireNonNull(id, "O campo 'id' não pode ser nulo");

        validarCamposObrigatorios(usuario, senha, role);
        validarEmail(usuario);

        return new Usuario(
                id,
                usuario,
                SenhaCriptografada.criptografar(senha).valor(),
                role,
                dataCriacao,
                LocalDateTime.now()
        );
    }

    private static void validarCamposObrigatorios(
            String usuario,
            String senha,
            Role role
    ) {
        validarNaoVazio(usuario, "usuario");
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
            String usuario
    ) {
        if (!isValidEmail(usuario)) {
            throw new UsuarioInvalidoException("O campo 'usuario' não é um e-mail válido");
        }
    }


}
