package br.com.wells.core.domain.pagamento.usecases.impl;

import br.com.wells.core.domain.pagamento.gateways.CriarPagamentoGateway;
import br.com.wells.core.domain.pagamento.model.Pagamento;
import br.com.wells.core.domain.pagamento.usecases.CriarPagamentoUseCase;

public class CriarPagamentoUseCaseImpl implements CriarPagamentoUseCase {

	private final CriarPagamentoGateway criarPagamentoGateway;

	public CriarPagamentoUseCaseImpl(CriarPagamentoGateway criarPagamentoGateway) {
		this.criarPagamentoGateway = criarPagamentoGateway;
	}

	@Override
	public Pagamento execute(Pagamento pagamento) {
		return criarPagamentoGateway.execute(pagamento);
	}

}
