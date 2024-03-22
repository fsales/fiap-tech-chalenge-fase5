package br.com.wells.core.domain.pagamento.model.enumeration;

import java.util.Arrays;
import java.util.Optional;

public enum ModalidadeCartao {
    CARTAO_CREDITO("Cartão de Crédito"),
    VALE_REFEICAO("Vale Refeição"),
    CARTAO_DEBITO("Cartão de Débito");

    private final String descricao;

    ModalidadeCartao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Optional<ModalidadeCartao> fromDescricao(String descricao) {
        return Arrays.stream(values())
                .filter(modalidade -> modalidade.getDescricao().equalsIgnoreCase(descricao))
                .findFirst();
    }
}

