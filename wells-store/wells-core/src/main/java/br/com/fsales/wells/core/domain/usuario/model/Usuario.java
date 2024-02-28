package br.com.fsales.wells.core.domain.usuario.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import br.com.fsales.wells.core.domain.usuario.exception.UsuarioInvalidoException;


public record Usuario(
        Optional<Long> id,
        String usuario,
        String senha,
        Role role,
        Optional<LocalDateTime> dataCriacao,
        Optional<LocalDateTime> dataAlteracao
) {

    public Usuario(
            Long id,
            String usuario,
            String senha,
            Role role,
            LocalDateTime dataCriacao,
            LocalDateTime dataAlteracao
    ) {
        this(
                Optional.of(id),
                usuario,
                senha,
                role,
                Optional.of(dataCriacao),
                Optional.of(dataAlteracao)
        );

    }

    public static Usuario criar(
            String usuario,
            String senha,
            Role role
    ) {
        validarCamposObrigatorios(usuario, senha, role);

        validarEmail(usuario);

        var senhaCriptografada = SenhaCriptografada.criptografar(senha);

        return new Usuario(
                Optional.empty(),
                usuario,
                senhaCriptografada.valor(),
                role,
                Optional.empty(),
                Optional.empty()
        );
    }

    public Usuario alterar(
            Long id,
            String senha,
            Role role
    ) {
        Objects.requireNonNull(id, "O campo 'id' não pode ser nulo");

        validarCamposObrigatorios(
                usuario,
                senha,
                role
        );

        validarEmail(usuario);

        var senhaCriptografada = SenhaCriptografada.criptografar(senha);

        return new Usuario(
                Optional.of(id),
                usuario,
                senhaCriptografada.valor(),
                role,
                dataCriacao,
                dataAlteracao
        );
    }

    private static void validarCamposObrigatorios(
            String usuario,
            String senha,
            Role role
    ) {
        Objects.requireNonNull(usuario, "O campo 'usuario' não pode ser nulo");
        Objects.requireNonNull(senha, "O campo 'senha' não pode ser nulo");
        Objects.requireNonNull(role, "O campo 'role' não pode ser nulo");
    }

    private static void validarEmail(
            String usuario
    ) {
        if (
                usuario != null &&
                !usuario.matches("^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")
        ) {
            throw new UsuarioInvalidoException("O campo 'usuario' não tem um e-mail válido");
        }
    }
}
