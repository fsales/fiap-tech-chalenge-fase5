package br.com.wells.core.domain.pagamento.gateways;

import br.com.wells.core.domain.pagamento.model.Pagamento;

public interface ConfirmarPagamentoGateway {

	Pagamento execute(Pagamento pagamento);

}
