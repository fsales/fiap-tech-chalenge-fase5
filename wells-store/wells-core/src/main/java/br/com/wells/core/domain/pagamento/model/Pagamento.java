package br.com.wells.core.domain.pagamento.model;

import java.time.LocalDate;
import java.util.Objects;

import br.com.wells.core.domain.pagamento.model.enumeration.StatusPagamento;
import br.com.wells.core.domain.pagamento.model.enumeration.TipoCartao;

public record Pagamento(Long id, double valor, String nome, String numero, LocalDate expiracao, String codigo,
		StatusPagamento status, TipoCartao tipoCartao, Long carrinhoId) {
    public Pagamento {
        Objects.requireNonNull(id,"O campo 'id' é obrigatório.");
        Objects.requireNonNull(nome, "O campo 'nome' é obrigatório.");
        Objects.requireNonNull(numero, "O campo 'numero' é obrigatório.");
        Objects.requireNonNull(expiracao, "O campo 'expiracao' é obrigatório.");
        Objects.requireNonNull(codigo, "O campo 'codigo' é obrigatório.");
        Objects.requireNonNull(status, "O campo 'status' é obrigatório.");
        Objects.requireNonNull(tipoCartao, "O campo 'tipoCartao' é obrigatório.");
        Objects.requireNonNull(carrinhoId, "O campo 'carrinhoId' é obrigatório.");

        validarCampoNaoVazio(nome, "nome");
        validarCampoNaoVazio(numero, "numero");
        validarCampoNaoVazio(codigo, "codigo");
    }

    private void validarCampoNaoVazio(String valor, String nomeCampo) {
        if (valor.trim().isEmpty()) {
            throw new IllegalArgumentException("O campo '" + nomeCampo + "' não pode estar em branco.");
        }
    }
}
