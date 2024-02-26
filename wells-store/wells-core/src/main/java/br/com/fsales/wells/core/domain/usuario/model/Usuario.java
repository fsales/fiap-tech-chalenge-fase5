package br.com.fsales.wells.core.domain.usuario.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;


public record Usuario(
        Optional<Long> id,
        String usuario,
        String senha,
        Role role,
        Optional<LocalDateTime> dataCriacao,
        Optional<LocalDateTime> dataAlteracao
) {

    public Usuario(Long id, String usuario, String senha, Role role, LocalDateTime dataCriacao, LocalDateTime dataAlteracao) {
        this(Optional.of(id), usuario, senha, role, Optional.of(dataCriacao), Optional.of(dataAlteracao));

    }

    public static Usuario criar(
            String usuario,
            String senha,
            Role role
    ) {
        Objects.requireNonNull(usuario, "O campo 'usuario' não pode ser nulo");
        Objects.requireNonNull(senha, "O campo 'senha' não pode ser nulo");
        Objects.requireNonNull(role, "O campo 'role' não pode ser nulo");

        return new Usuario(Optional.empty(), usuario, senha, role, Optional.empty(), Optional.empty());
    }

    public Usuario alterar(
            Long id,
            String senha,
            Role role
    ) {
        Objects.requireNonNull(id, "O campo 'id' não pode ser nulo");
        Objects.requireNonNull(senha, "O campo 'senha' não pode ser nulo");
        Objects.requireNonNull(role, "O campo 'role' não pode ser nulo");

        return new Usuario(
                Optional.of(id),
                usuario,
                senha,
                role,
                dataCriacao,
                dataAlteracao
        );
    }
}
