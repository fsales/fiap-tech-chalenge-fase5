package br.com.wells.wellspagamento.presentation.rest.controller.pagamento.swagger;

import br.com.wells.wellspagamento.presentation.rest.controller.generic.dto.response.GenericResponse;
import br.com.wells.wellspagamento.presentation.rest.controller.pagamento.dto.request.PagamentoRequest;
import br.com.wells.wellspagamento.presentation.rest.controller.pagamento.dto.response.PagamentoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "PagamentoController", description = "Controlador para gerenciar operações de pagamento")
public interface PagamentoControllerSwagger {

	@Operation(summary = "Cria um novo pagamento",
			responses = {
					@ApiResponse(responseCode = "201", description = "Pagamento criado com sucesso",
							content = @Content(schema = @Schema(implementation = PagamentoResponse.class))),
					@ApiResponse(responseCode = "400", description = "Requisição inválida"),
					@ApiResponse(responseCode = "409", description = "Conflito de dados"),
					@ApiResponse(responseCode = "422", description = "Dados inválidos"),
					@ApiResponse(responseCode = "500", description = "Erro interno do servidor") })
	ResponseEntity<GenericResponse<PagamentoResponse>> cadastrar(
			@Parameter(description = "Dados do pagamento a ser criado") PagamentoRequest pagamentoRequest,
			UriComponentsBuilder uriComponentsBuilder);

	@Operation(summary = "Atualiza um pagamento existente",
			responses = {
					@ApiResponse(responseCode = "200", description = "Pagamento atualizado com sucesso",
							content = @Content(schema = @Schema(implementation = PagamentoResponse.class))),
					@ApiResponse(responseCode = "400", description = "Requisição inválida"),
					@ApiResponse(responseCode = "404", description = "Pagamento não encontrado"),
					@ApiResponse(responseCode = "409", description = "Conflito de dados"),
					@ApiResponse(responseCode = "422", description = "Dados inválidos"),
					@ApiResponse(responseCode = "500", description = "Erro interno do servidor") })
	ResponseEntity<GenericResponse<PagamentoResponse>> atualizar(
			@Parameter(description = "ID do pagamento a ser atualizado") Long id,
			@Parameter(description = "Dados atualizados do pagamento") PagamentoRequest pagamentoRequest);

	@Operation(summary = "Confirma um pagamento",
			responses = { @ApiResponse(responseCode = "200", description = "Pagamento confirmado com sucesso"),
					@ApiResponse(responseCode = "404", description = "Pagamento não encontrado"),
					@ApiResponse(responseCode = "500", description = "Erro interno do servidor") })
	void confirmarPagamento(@Parameter(description = "ID do pagamento a ser confirmado") Long id);

	@Operation(summary = "Remove um pagamento",
			responses = { @ApiResponse(responseCode = "204", description = "Pagamento removido com sucesso"),
					@ApiResponse(responseCode = "404", description = "Pagamento não encontrado"),
					@ApiResponse(responseCode = "500", description = "Erro interno do servidor") })
	ResponseEntity<Void> remover(@Parameter(description = "ID do pagamento a ser removido") Long id);

	@Operation(summary = "Consulta um pagamento por ID",
			responses = {
					@ApiResponse(responseCode = "200", description = "Pagamento encontrado",
							content = @Content(schema = @Schema(implementation = PagamentoResponse.class))),
					@ApiResponse(responseCode = "404", description = "Pagamento não encontrado"),
					@ApiResponse(responseCode = "500", description = "Erro interno do servidor") })
	ResponseEntity<GenericResponse<PagamentoResponse>> consultarPagamentoPorId(
			@Parameter(description = "ID do pagamento a ser consultado") Long id);

	@Operation(summary = "Lista todos os pagamentos",
			responses = {
					@ApiResponse(responseCode = "200", description = "Lista de pagamentos",
							content = @Content(schema = @Schema(implementation = PagamentoResponse.class))),
					@ApiResponse(responseCode = "500", description = "Erro interno do servidor") })
	ResponseEntity<GenericResponse<Page<PagamentoResponse>>> listarTodos(Pageable pageable);

}
