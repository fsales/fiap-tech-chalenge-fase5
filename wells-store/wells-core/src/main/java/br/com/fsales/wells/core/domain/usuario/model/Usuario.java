package br.com.fsales.wells.core.domain.usuario.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

public record Usuario(
        String username,
        String password,
        Role role,
        LocalDateTime dataCriacao,
        Optional<LocalDateTime> dataAlteracao
) {

    public static Usuario criar(String username, String password, Role role) {
        Objects.requireNonNull(username, "O campo 'username' não pode ser nulo");
        Objects.requireNonNull(password, "O campo 'password' não pode ser nulo");
        Objects.requireNonNull(role, "O campo 'role' não pode ser nulo");

        return new Usuario(username, password, role, LocalDateTime.now(), Optional.empty());
    }

    public Usuario alterar(LocalDateTime novaDataAlteracao, String password, Role role) {
        Objects.requireNonNull(novaDataAlteracao, "O campo 'novaDataAlteracao' não pode ser nulo");

        return new Usuario(username, password, role, dataCriacao, Optional.of(novaDataAlteracao));
    }

    public static void main(String[] args) {
        var a = Usuario.criar(
                "",
                "",
                Role.ADMIN
        );
    }
}
