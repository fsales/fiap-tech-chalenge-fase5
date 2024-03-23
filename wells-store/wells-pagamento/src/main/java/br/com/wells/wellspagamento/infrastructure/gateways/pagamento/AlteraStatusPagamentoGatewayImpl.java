package br.com.wells.wellspagamento.infrastructure.gateways.pagamento;

import br.com.wells.core.domain.pagamento.gateways.AlteraStatusPagamentoGateway;
import br.com.wells.wellspagamento.infrastructure.database.postgres.repository.PagamentoEntityRepository;

public class AlteraStatusPagamentoGatewayImpl implements AlteraStatusPagamentoGateway {

	private final PagamentoEntityRepository pagamentoEntityRepository;

	public AlteraStatusPagamentoGatewayImpl(PagamentoEntityRepository pagamentoEntityRepository) {
		this.pagamentoEntityRepository = pagamentoEntityRepository;
	}

}
