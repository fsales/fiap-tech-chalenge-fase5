package br.com.wells.core.domain.pagamento.usecases.impl;

import br.com.wells.core.domain.pagamento.gateways.ConfirmarPagamentoGateway;
import br.com.wells.core.domain.pagamento.usecases.ConfirmarPagamentoUseCase;

public class ConfirmarPagamentoUseCaseImpl implements ConfirmarPagamentoUseCase {

	private final ConfirmarPagamentoGateway confirmarPagamentoGateway;

	public ConfirmarPagamentoUseCaseImpl(ConfirmarPagamentoGateway confirmarPagamentoGateway) {
		this.confirmarPagamentoGateway = confirmarPagamentoGateway;
	}

}
