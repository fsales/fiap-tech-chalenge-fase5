package br.com.wells.wellspagamento.infrastructure.gateways.pagamento;

import br.com.wells.core.domain.pagamento.gateways.ExcluirPagamentoGateway;
import br.com.wells.wellspagamento.infrastructure.database.postgres.repository.PagamentoEntityRepository;

public class ExcluirPagamentoGatewayImpl implements ExcluirPagamentoGateway {

	private final PagamentoEntityRepository pagamentoEntityRepository;

	public ExcluirPagamentoGatewayImpl(PagamentoEntityRepository pagamentoEntityRepository) {
		this.pagamentoEntityRepository = pagamentoEntityRepository;
	}

}
