package br.com.wells.core.domain.pagamento.gateways;

import br.com.wells.core.domain.pagamento.model.Pagamento;

public interface AtualizarPagamentoGateway {

	Pagamento execute(final Long id, final Pagamento pagamento);

}
