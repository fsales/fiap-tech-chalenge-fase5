package br.com.wells.wellspagamento.infrastructure.gateways.pagamento;

import br.com.wells.core.domain.exception.WellsStoreUniqueViolationException;
import br.com.wells.core.domain.pagamento.gateways.ExcluirPagamentoGateway;
import br.com.wells.core.domain.exception.WellsStoreEntityNotFoundException;
import br.com.wells.wellspagamento.infrastructure.database.postgres.repository.PagamentoEntityRepository;
import org.springframework.dao.DataIntegrityViolationException;

public class ExcluirPagamentoGatewayImpl implements ExcluirPagamentoGateway {

	private final PagamentoEntityRepository pagamentoEntityRepository;

	public ExcluirPagamentoGatewayImpl(PagamentoEntityRepository pagamentoEntityRepository) {
		this.pagamentoEntityRepository = pagamentoEntityRepository;
	}

	@Override
	public void execute(final Long id) {
		try {
			var pagamento = pagamentoEntityRepository.findById(id)
				.orElseThrow(() -> new WellsStoreEntityNotFoundException("Pagamento não encontrado"));

			pagamentoEntityRepository.deleteById(pagamento.getId());
		}
		catch (DataIntegrityViolationException e) {
			throw new WellsStoreUniqueViolationException("Pagamento não pode ser excluído.", e);
		}
	}

}
