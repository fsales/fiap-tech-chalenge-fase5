package br.com.wells.wellspagamento.presentation.rest.controller.pagamento;

import br.com.wells.wellspagamento.infrastructure.spring.config.app.ApiRoutes;
import br.com.wells.wellspagamento.presentation.rest.controller.generic.dto.response.GenericResponse;
import br.com.wells.wellspagamento.presentation.rest.controller.generic.validation.CreateInfo;
import br.com.wells.wellspagamento.presentation.rest.controller.generic.validation.UpdateInfo;
import br.com.wells.wellspagamento.presentation.rest.controller.pagamento.dto.request.PagamentoRequest;
import br.com.wells.wellspagamento.presentation.rest.controller.pagamento.dto.response.PagamentoResponse;
import br.com.wells.wellspagamento.presentation.rest.controller.pagamento.swagger.PagamentoControllerSwagger;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
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
public class PagamentoController implements PagamentoControllerSwagger {

	@PostMapping
	@Override
	public ResponseEntity<GenericResponse<PagamentoResponse>> cadastrar(
			@Validated(CreateInfo.class) @RequestBody PagamentoRequest pagamentoRequest,
			UriComponentsBuilder uriComponentsBuilder) {
		PagamentoResponse pagamentoResponse = null;
		var uri = ApiRoutes.construirUriPagamentoPorId(pagamentoResponse.id());

		return ResponseEntity.created(uri).body(GenericResponse.success(HttpStatus.OK, pagamentoResponse));
	}

	@PatchMapping("/{id}")
	@Override
	public ResponseEntity<GenericResponse<PagamentoResponse>> atualizar(@PathVariable @NotNull Long id,
			@RequestBody @Validated(UpdateInfo.class) PagamentoRequest pagamentoRequest) {

		return ResponseEntity.ok(GenericResponse.success(HttpStatus.OK, null));
	}

	@PatchMapping("/{id}/confirmar")
	@Override
	public void confirmarPagamento(@PathVariable @NotNull Long id) {

	}

	@DeleteMapping("/{id}")
	@Override
	public ResponseEntity<Void> remover(@PathVariable @NotNull Long id) {

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	@Override
	public ResponseEntity<GenericResponse<PagamentoResponse>> consultarPagamentoPorId(@PathVariable @NotNull Long id) {

		return ResponseEntity.ok(GenericResponse.success(HttpStatus.OK, null));
	}

	@GetMapping
	@Override
	public ResponseEntity<GenericResponse<Page<PagamentoResponse>>> listarTodos(
			@PageableDefault(page = 0, size = 10) Pageable pageable) {

		return ResponseEntity.ok(GenericResponse.success(HttpStatus.OK, null));
	}

}
