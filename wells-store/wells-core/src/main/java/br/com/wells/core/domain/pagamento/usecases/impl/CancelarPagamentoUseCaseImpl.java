package br.com.wells.core.domain.pagamento.usecases.impl;

import br.com.wells.core.domain.pagamento.gateways.CancelarPagamentoGateway;
import br.com.wells.core.domain.pagamento.usecases.CancelarPagamentoUseCase;

public class CancelarPagamentoUseCaseImpl implements CancelarPagamentoUseCase {

	private final CancelarPagamentoGateway cancelarPagamentoGateway;

	public CancelarPagamentoUseCaseImpl(CancelarPagamentoGateway cancelarPagamentoGateway) {
		this.cancelarPagamentoGateway = cancelarPagamentoGateway;
	}

	public void execute(Long id) {
		cancelarPagamentoGateway.execute(id);
	}

}
