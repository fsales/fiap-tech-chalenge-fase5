package br.com.wells.core.domain.pagamento.gateways;

import java.util.Optional;

import br.com.wells.core.domain.pagamento.model.Pagamento;

public interface ConsultarPagamentoPorIdGateway {

	Optional<Pagamento> execute(Long id);

}
