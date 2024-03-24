package br.com.wells.core.domain.pagamento.usecases.impl;

import br.com.wells.core.domain.pagamento.gateways.ConfirmarPagamentoGateway;
import br.com.wells.core.domain.pagamento.model.Pagamento;
import br.com.wells.core.domain.pagamento.usecases.ConfirmarPagamentoUseCase;
import br.com.wells.core.domain.pedido.gateways.ConfirmarPagamentoPedido;

public class ConfirmarPagamentoUseCaseImpl implements ConfirmarPagamentoUseCase {

	private final ConfirmarPagamentoGateway confirmarPagamentoGateway;

	private final ConfirmarPagamentoPedido confirmarPagamentoPedido;

	public ConfirmarPagamentoUseCaseImpl(ConfirmarPagamentoGateway confirmarPagamentoGateway,
			ConfirmarPagamentoPedido confirmarPagamentoPedido) {
		this.confirmarPagamentoGateway = confirmarPagamentoGateway;
		this.confirmarPagamentoPedido = confirmarPagamentoPedido;
	}

	@Override
	public void execute(Long id) {

		var pagamento = confirmarPagamentoGateway.execute(new Pagamento(id));
		confirmarPagamentoPedido.execute(pagamento.id());
	}

}
