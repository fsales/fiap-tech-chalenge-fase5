package br.com.wells.wellspagamento.infrastructure.gateways.pagamento;

import br.com.wells.core.domain.exception.WellsStoreEntityNotFoundException;
import br.com.wells.core.domain.exception.WellsStoreUniqueViolationException;
import br.com.wells.core.domain.pagamento.gateways.AtualizarPagamentoGateway;
import br.com.wells.core.domain.pagamento.model.Pagamento;
import br.com.wells.wellspagamento.infrastructure.database.postgres.entity.PagamentoEntity;
import br.com.wells.wellspagamento.infrastructure.database.postgres.repository.PagamentoEntityRepository;
import br.com.wells.wellspagamento.infrastructure.gateways.pagamento.mapper.PagamentoEntityMapper;
import org.springframework.dao.DataIntegrityViolationException;

public class AtualizarPagamentoGatewayImpl implements AtualizarPagamentoGateway {

	private final PagamentoEntityRepository pagamentoEntityRepository;

	String PAGAMENTO_JA_CADASTRADO = "Pagamento já cadastrado";

	String PAGAMENTO_NAO_ENCONTRADO = "Pagamento não encontrado";

	public AtualizarPagamentoGatewayImpl(PagamentoEntityRepository pagamentoEntityRepository) {
		this.pagamentoEntityRepository = pagamentoEntityRepository;
	}

	@Override
	public Pagamento execute(final Long id, final Pagamento pagamento) {
		PagamentoEntity pagamentoEntity = null;

		try {

			pagamentoEntity = pagamentoEntityRepository
				.save(PagamentoEntityMapper.update(pagamentoEntityRepository.getReferenceById(id), pagamento));
		}
		catch (jakarta.persistence.EntityNotFoundException e) {
			throw new WellsStoreEntityNotFoundException(PAGAMENTO_NAO_ENCONTRADO, e);
		}
		catch (DataIntegrityViolationException e) {
			throw new WellsStoreUniqueViolationException(PAGAMENTO_JA_CADASTRADO, e);
		}

		return PagamentoEntityMapper.convertToPagamento(pagamentoEntity);
	}

}
