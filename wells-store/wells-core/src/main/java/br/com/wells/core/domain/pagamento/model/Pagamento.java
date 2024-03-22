package br.com.wells.core.domain.pagamento.model;

import java.time.LocalDate;

import br.com.wells.core.domain.pagamento.model.enumeration.StatusPagamento;
import br.com.wells.core.domain.pagamento.model.enumeration.TipoCartao;
import br.com.wells.core.domain.usuario.exception.PagamentoInvalidoException;

public record Pagamento(Long id, double valor, String nome, String numero, LocalDate expiracao, String codigo,
		StatusPagamento status, TipoCartao tipoCartao, Long carrinhoId) {
	public Pagamento {
		if (nome == null || numero == null || expiracao == null || codigo == null || status == null
				|| tipoCartao == null || carrinhoId == null) {
			throw new PagamentoInvalidoException("Todos os campos obrigatórios devem ser fornecidos.");
		}
	}
}
