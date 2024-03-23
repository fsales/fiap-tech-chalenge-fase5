package br.com.wells.core.domain.pagamento.usecases.impl;

import br.com.wells.core.domain.pagamento.gateways.AtualizarPagamentoGateway;
import br.com.wells.core.domain.pagamento.model.Pagamento;
import br.com.wells.core.domain.pagamento.usecases.AtualizarPagamentoUseCase;

public class AtualizarPagamentoUseCaseImpl implements AtualizarPagamentoUseCase {

	private final AtualizarPagamentoGateway atualizarPagamentoGateway;

	public AtualizarPagamentoUseCaseImpl(AtualizarPagamentoGateway atualizarPagamentoGateway) {
		this.atualizarPagamentoGateway = atualizarPagamentoGateway;
	}

	@Override
	public Pagamento execute(Long id, Pagamento pagamento) {
		return atualizarPagamentoGateway.execute(id, pagamento);
	}

}
