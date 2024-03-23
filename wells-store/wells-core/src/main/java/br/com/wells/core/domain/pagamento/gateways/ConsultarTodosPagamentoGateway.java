package br.com.wells.core.domain.pagamento.gateways;

import br.com.wells.core.domain.pagamento.model.Pagamento;
import br.com.wells.core.domain.page.Pagina;

public interface ConsultarTodosPagamentoGateway {

	Pagina<Pagamento> execute(final int pageNumber, final int pageSize);

}
