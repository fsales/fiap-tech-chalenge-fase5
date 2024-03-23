package br.com.wells.wellspagamento.infrastructure.gateways.pagamento;

import br.com.wells.core.domain.exception.WellsStoreUniqueViolationException;
import br.com.wells.core.domain.pagamento.gateways.CancelarPagamentoGateway;
import br.com.wells.core.domain.exception.WellsStoreEntityNotFoundException;
import br.com.wells.core.domain.pagamento.model.Pagamento;
import br.com.wells.wellspagamento.infrastructure.database.postgres.entity.enumeration.StatusPagamentoEnum;
import br.com.wells.wellspagamento.infrastructure.database.postgres.repository.PagamentoEntityRepository;
import br.com.wells.wellspagamento.infrastructure.gateways.pagamento.mapper.PagamentoEntityMapper;
import org.springframework.dao.DataIntegrityViolationException;

public class CancelarPagamentoGatewayImpl implements CancelarPagamentoGateway {

	private final PagamentoEntityRepository pagamentoEntityRepository;

	public CancelarPagamentoGatewayImpl(PagamentoEntityRepository pagamentoEntityRepository) {
		this.pagamentoEntityRepository = pagamentoEntityRepository;
	}

	@Override
	public Pagamento execute(final Long id) {
		try {
			var pagamento = pagamentoEntityRepository.findById(id)
				.orElseThrow(() -> new WellsStoreEntityNotFoundException("Pagamento não encontrado"));

			pagamento.setStatus(StatusPagamentoEnum.CANCELADO);

			return PagamentoEntityMapper.convertToPagamento(pagamentoEntityRepository.save(pagamento));
		}
		catch (DataIntegrityViolationException e) {
			throw new WellsStoreUniqueViolationException("Pagamento não pode ser cancelado.", e);
		}
	}

}
