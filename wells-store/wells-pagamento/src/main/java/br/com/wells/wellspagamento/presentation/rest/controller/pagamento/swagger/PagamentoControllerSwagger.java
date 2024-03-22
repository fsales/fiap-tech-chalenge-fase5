package br.com.wells.wellspagamento.presentation.rest.controller.pagamento.swagger;

import br.com.wells.wellspagamento.presentation.rest.controller.generic.dto.response.GenericResponse;
import br.com.wells.wellspagamento.presentation.rest.controller.pagamento.dto.request.PagamentoRequest;
import br.com.wells.wellspagamento.presentation.rest.controller.pagamento.dto.response.PagamentoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

public interface PagamentoControllerSwagger {

	ResponseEntity<GenericResponse<PagamentoResponse>> cadastrar(PagamentoRequest pagamentoRequest,
			UriComponentsBuilder uriComponentsBuilder);

	ResponseEntity<GenericResponse<PagamentoResponse>> atualizar(Long id, PagamentoRequest pagamentoRequest);

	void confirmarPagamento(Long id);

	ResponseEntity<Void> remover(Long id);

	ResponseEntity<GenericResponse<PagamentoResponse>> consultarPagamentoPorId(Long id);

	ResponseEntity<GenericResponse<Page<PagamentoResponse>>> listarTodos(Pageable pageable);

}
