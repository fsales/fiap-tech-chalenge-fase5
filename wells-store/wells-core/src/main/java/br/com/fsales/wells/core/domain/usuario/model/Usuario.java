package br.com.fsales.wells.core.domain.usuario.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

public record Usuario(
        String usuario,
        String senha,
        Role role,
        LocalDateTime dataCriacao,
        Optional<LocalDateTime> dataAlteracao
) {

    public static Usuario criar(String usuario, String senha, Role role) {
        Objects.requireNonNull(usuario, "O campo 'usuario' não pode ser nulo");
        Objects.requireNonNull(senha, "O campo 'senha' não pode ser nulo");
        Objects.requireNonNull(role, "O campo 'role' não pode ser nulo");

        return new Usuario(usuario, senha, role, LocalDateTime.now(), Optional.empty());
    }

    public Usuario alterar(LocalDateTime novaDataAlteracao, String senha, Role role) {
        Objects.requireNonNull(novaDataAlteracao, "O campo 'novaDataAlteracao' não pode ser nulo");

        return new Usuario(usuario, senha, role, dataCriacao, Optional.of(novaDataAlteracao));
    }
}
