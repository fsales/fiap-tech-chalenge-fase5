package br.com.wells.core.domain.pagamento.usecases;

import br.com.wells.core.domain.pagamento.model.Pagamento;
import br.com.wells.core.domain.page.Pagina;

public interface ConsultarTodosPagamentoUseCase {

	Pagina<Pagamento> execute(int pageNumber, int pageSize);

}
