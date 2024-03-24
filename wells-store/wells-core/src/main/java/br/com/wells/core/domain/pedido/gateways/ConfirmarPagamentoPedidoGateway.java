package br.com.wells.core.domain.pedido.gateways;

import br.com.wells.core.domain.pagamento.model.Pagamento;

public interface ConfirmarPagamentoPedidoGateway {

	Pagamento execute(final Pagamento pagamento);

}
