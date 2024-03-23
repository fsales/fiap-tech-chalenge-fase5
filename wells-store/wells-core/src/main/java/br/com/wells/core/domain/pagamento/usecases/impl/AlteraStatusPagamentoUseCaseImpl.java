package br.com.wells.core.domain.pagamento.usecases.impl;

import br.com.wells.core.domain.pagamento.gateways.AlteraStatusPagamentoGateway;
import br.com.wells.core.domain.pagamento.usecases.AlteraStatusPagamentoUseCase;

public class AlteraStatusPagamentoUseCaseImpl implements AlteraStatusPagamentoUseCase {

	private final AlteraStatusPagamentoGateway alteraStatusPagamentoGateway;

	public AlteraStatusPagamentoUseCaseImpl(AlteraStatusPagamentoGateway alteraStatusPagamentoGateway) {
		this.alteraStatusPagamentoGateway = alteraStatusPagamentoGateway;
	}

}
