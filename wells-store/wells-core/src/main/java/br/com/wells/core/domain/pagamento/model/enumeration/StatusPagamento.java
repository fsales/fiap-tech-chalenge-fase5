package br.com.wells.core.domain.pagamento.model.enumeration;

import java.util.Arrays;

public enum StatusPagamento {
    CRIADO("Criado"),
    CONFIRMADO("Confirmado"),
    CANCELADO("Cancelado");

    private final String descricao;

    StatusPagamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    // Método para converter uma string em um enum StatusPagamento
    public static StatusPagamento fromString(String texto) {
        return Arrays.stream(values())
                .filter(status -> status.descricao.equalsIgnoreCase(texto))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Nenhum status de pagamento encontrado com a descrição fornecida: " + texto));
    }
}
