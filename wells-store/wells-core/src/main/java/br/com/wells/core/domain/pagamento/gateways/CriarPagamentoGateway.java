package br.com.wells.core.domain.pagamento.gateways;

import br.com.wells.core.domain.pagamento.model.Pagamento;

public interface CriarPagamentoGateway {

	Pagamento execute(final Pagamento pagamento);

}
