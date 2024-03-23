package br.com.wells.wellspagamento.infrastructure.gateways.pagamento;

import br.com.wells.core.domain.pagamento.gateways.ConfirmarPagamentoGateway;
import br.com.wells.wellspagamento.infrastructure.database.postgres.repository.PagamentoEntityRepository;

public class ConfirmarPagamentoGatewayImpl implements ConfirmarPagamentoGateway {

	private final PagamentoEntityRepository pagamentoEntityRepository;

	public ConfirmarPagamentoGatewayImpl(PagamentoEntityRepository pagamentoEntityRepository) {
		this.pagamentoEntityRepository = pagamentoEntityRepository;
	}

}
