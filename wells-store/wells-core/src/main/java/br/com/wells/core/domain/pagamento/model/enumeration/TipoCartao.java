package br.com.wells.core.domain.pagamento.model.enumeration;

import java.util.Arrays;
import java.util.Optional;

public enum TipoCartao {
    VISA_CREDITO("Visa", ModalidadeCartao.CARTAO_CREDITO),
    MASTERCARD_CREDITO("MasterCard", ModalidadeCartao.CARTAO_CREDITO),
    AMEX_CREDITO("Amex", ModalidadeCartao.CARTAO_CREDITO),
    ELO_CREDITO("Elo", ModalidadeCartao.CARTAO_CREDITO),
    ALELO_VALE_REFEICAO("Alelo", ModalidadeCartao.VALE_REFEICAO),
    TICKET_RESTAURANTE("Ticket Restaurante", ModalidadeCartao.VALE_REFEICAO),
    MASTERCARD_MAESTRO_DEBITO("MasterCard Maestro", ModalidadeCartao.CARTAO_DEBITO),
    VISA_DEBITO("Visa Débito", ModalidadeCartao.CARTAO_DEBITO),
    ELO_DEBITO("Elo", ModalidadeCartao.CARTAO_DEBITO);

    private final String nomeCartao;
    private final ModalidadeCartao modalidade;

    TipoCartao(String nomeCartao, ModalidadeCartao modalidade) {
        this.nomeCartao = nomeCartao;
        this.modalidade = modalidade;
    }

    public String getNomeCartao() {
        return nomeCartao;
    }

    public ModalidadeCartao getModalidade() {
        return modalidade;
    }

    public static Optional<TipoCartao> fromSigla(String sigla) {
        return Arrays.stream(values())
                .filter(tipo -> tipo.name().equalsIgnoreCase(sigla))
                .findFirst();
    }
}
