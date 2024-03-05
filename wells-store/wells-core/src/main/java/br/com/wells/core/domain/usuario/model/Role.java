package br.com.wells.core.domain.usuario.model;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Role {

    private final String nome;
    private Long id;

    private Role(@NonNull String nome) {
        validarNaoVazio(nome, "nome");
        this.nome = nome;
    }

    public static Role criar(@NonNull String nome) {
        return new Role(nome);
    }

    public void id(Long id) {
        this.id = id;
    }

    public String nome() {
        return nome;
    }

    public Long id() {
        return id;
    }

    private static void validarNaoVazio(
            final String campo,
            final String nomeCampo
    ) {
        if (campo == null || campo.trim().isEmpty()) {
            throw new IllegalArgumentException(String.format("O campo '%s' é obrigatório e não pode consistir apenas em espaços em branco.", nomeCampo));
        }
    }
}
