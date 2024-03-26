package br.com.wells.core.domain.pagamento.usecases.impl;

import java.util.logging.Logger;

import br.com.wells.core.domain.exception.WellsStoreDataIntegrityViolationException;
import br.com.wells.core.domain.pagamento.gateways.ConfirmarPagamentoGateway;
import br.com.wells.core.domain.pagamento.model.Pagamento;
import br.com.wells.core.domain.pagamento.usecases.ConfirmarPagamentoUseCase;
import br.com.wells.core.domain.pagamento.usecases.ConsultarPagamentoPorIdUseCase;

public class ConfirmarPagamentoUseCaseImpl implements ConfirmarPagamentoUseCase {

	private static final Logger logger = Logger.getLogger(ConfirmarPagamentoUseCaseImpl.class.getName());

	private final ConfirmarPagamentoGateway confirmarPagamentoGateway;

	private final ConsultarPagamentoPorIdUseCase consultarPagamentoPorIdUseCase;

	public ConfirmarPagamentoUseCaseImpl(ConfirmarPagamentoGateway confirmarPagamentoGateway,
			ConsultarPagamentoPorIdUseCase consultarPagamentoPorIdUseCase) {
		this.confirmarPagamentoGateway = confirmarPagamentoGateway;
		this.consultarPagamentoPorIdUseCase = consultarPagamentoPorIdUseCase;
	}

	@Override
	public Pagamento execute(Long id) {
		logger.info("Confirmando pagamento: " + id);

		var pagamento = consultarPagamentoPorIdUseCase.execute(id);
		if (pagamento.isConfirmado()) {
			throw new WellsStoreDataIntegrityViolationException("Pagamento já confirmado");
		}

		if (pagamento.isCancelado()) {
			throw new WellsStoreDataIntegrityViolationException("Pagamento cancelado");
		}

		return confirmarPagamentoGateway.execute(pagamento);
	}

}
