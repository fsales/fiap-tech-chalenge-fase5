package br.com.wells.wellspagamento.presentation.rest.controller.pagamento;

import br.com.wells.core.domain.pagamento.usecases.AtualizarPagamentoUseCase;
import br.com.wells.core.domain.pagamento.usecases.ConfirmarPagamentoUseCase;
import br.com.wells.core.domain.pagamento.usecases.ConsultarPagamentoPorIdUseCase;
import br.com.wells.core.domain.pagamento.usecases.ConsultarTodosPagamentoUseCase;
import br.com.wells.core.domain.pagamento.usecases.CriarPagamentoUseCase;
import br.com.wells.core.domain.pagamento.usecases.CancelarPagamentoUseCase;
import br.com.wells.wellspagamento.infrastructure.spring.config.app.ApiRoutes;
import br.com.wells.wellspagamento.presentation.rest.controller.generic.dto.response.GenericResponse;
import br.com.wells.wellspagamento.presentation.rest.controller.generic.validation.CreateInfo;
import br.com.wells.wellspagamento.presentation.rest.controller.generic.validation.UpdateInfo;
import br.com.wells.wellspagamento.presentation.rest.controller.pagamento.dto.mapper.PagamentoDtoMapper;
import br.com.wells.wellspagamento.presentation.rest.controller.pagamento.dto.request.AtualizarPagamentoRequest;
import br.com.wells.wellspagamento.presentation.rest.controller.pagamento.dto.request.PagamentoRequest;
import br.com.wells.wellspagamento.presentation.rest.controller.pagamento.dto.response.PagamentoResponse;
import br.com.wells.wellspagamento.presentation.rest.controller.pagamento.swagger.PagamentoControllerSwagger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(ApiRoutes.PAGAMENTO_URI)

@RequiredArgsConstructor
@Slf4j
public class PagamentoController implements PagamentoControllerSwagger {

	private final AtualizarPagamentoUseCase atualizarPagamentoUseCase;

	private final ConfirmarPagamentoUseCase confirmarPagamentoUseCase;

	private final ConsultarPagamentoPorIdUseCase consultarPagamentoPorIdUseCase;

	private final ConsultarTodosPagamentoUseCase consultarTodosPagamentoUseCase;

	private final CriarPagamentoUseCase criarPagamentoUseCase;

	private final CancelarPagamentoUseCase cancelarPagamentoUseCase;

	@PostMapping
	@Override
	public ResponseEntity<GenericResponse<PagamentoResponse>> cadastrar(
			@Validated(CreateInfo.class) @RequestBody PagamentoRequest pagamentoRequest,
			UriComponentsBuilder uriComponentsBuilder) {

		log.info("Cadastrando pagamento: {}", pagamentoRequest);

		var pagamentoSalvo = criarPagamentoUseCase.execute(PagamentoDtoMapper.toPagamento(pagamentoRequest));
		var pagamentoResponse = PagamentoDtoMapper.toPagamentoResponse(pagamentoSalvo);
		var uri = ApiRoutes.construirUriPagamentoPorId(pagamentoResponse.id());

		return ResponseEntity.created(uri).body(GenericResponse.success(HttpStatus.OK, pagamentoResponse));
	}

	@PatchMapping("/{id}")

	@Override
	public ResponseEntity<GenericResponse<PagamentoResponse>> atualizar(@PathVariable Long id,
			@RequestBody @Validated(UpdateInfo.class) AtualizarPagamentoRequest pagamentoRequest) {

		log.info("Atualizando pagamento: {}", pagamentoRequest);

		var pagamento = atualizarPagamentoUseCase.execute(id, PagamentoDtoMapper.toPagamento(pagamentoRequest));

		return ResponseEntity
			.ok(GenericResponse.success(HttpStatus.OK, PagamentoDtoMapper.toPagamentoResponse(pagamento)));
	}

	@PatchMapping("/{id}/confirmar")
	@Override
	public ResponseEntity<Void> confirmarPagamento(@PathVariable Long id) {
		log.info("Confirmando pagamento: {}", id);

		confirmarPagamentoUseCase.execute(id);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	@Override
	public ResponseEntity<Void> cancelar(@PathVariable Long id) {
		log.info("Removendo pagamento: {}", id);

		cancelarPagamentoUseCase.execute(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	@Override
	public ResponseEntity<GenericResponse<PagamentoResponse>> consultarPagamentoPorId(@PathVariable Long id) {
		log.info("Consultando pagamento por id: {}", id);

		var pagamento = consultarPagamentoPorIdUseCase.execute(id);
		return ResponseEntity
			.ok(GenericResponse.success(HttpStatus.OK, PagamentoDtoMapper.toPagamentoResponse(pagamento)));
	}

	@GetMapping
	@Override
	public ResponseEntity<GenericResponse<Page<PagamentoResponse>>> listarTodos(
			@PageableDefault(page = 0, size = 10) Pageable pageable) {
		log.info("Listando todos os pagamentos");
		var pagamentoPagina = consultarTodosPagamentoUseCase.execute(pageable.getPageNumber(), pageable.getPageSize());

		var pagamentoPageResponses = new PageImpl<>(pagamentoPagina.list(), pageable, pagamentoPagina.totalElements())
			.map(PagamentoDtoMapper::toPagamentoResponse);

		return ResponseEntity.ok(GenericResponse.success(HttpStatus.OK, pagamentoPageResponses));
	}

}
