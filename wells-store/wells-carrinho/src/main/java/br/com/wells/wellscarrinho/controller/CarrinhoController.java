package br.com.wells.wellscarrinho.controller;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.wells.core.domain.pagamento.model.Pagamento;
import br.com.wells.wellscarrinho.infrastructure.amqp.processor.CriarPagamentoProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/carrinho")
public class CarrinhoController {

	private final CriarPagamentoProducer criarPagamentoProducer;

	public CarrinhoController(CriarPagamentoProducer criarPagamentoProducer) {
		this.criarPagamentoProducer = criarPagamentoProducer;
	}

	@PostMapping("/checkout")
	public void checkout() {
		criarPagamentoProducer.produceCriarPagamento(criarPagamento());
	}

	private Pagamento criarPagamento() {

		return Pagamento.criar(new BigDecimal("100.00"), // Valor do pagamento
				"João da Silva", // Nome do cliente
				"1234567890123456", // Número do cartão
				LocalDate.now().plusYears(1), // Data de expiração do cartão
				"123", // Código de segurança do cartão
				"Visa", // tipo de cartão
				1L // ID do pedido
		);
	}

}
