package br.com.wells.wellscarrinho.infrastructure.amqp.config;

import br.com.wells.wellscarrinho.infrastructure.amqp.constants.ExchangeNames;
import br.com.wells.wellscarrinho.infrastructure.amqp.constants.QueueNames;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PagamentoQueueConfiguration {

	@Bean
	public Binding bindPagamentoPedido() {
		return BindingBuilder.bind(pagamentoQueue()).to(fanoutExchange());
	}

	@Bean
	public Binding bindDlxPagamentoPedido() {
		return BindingBuilder.bind(pagamentoDLQ()).to(deadLetterExchangePagamento());
	}

	@Bean
	public Binding bindPagamentoAConfirmar() {
		return BindingBuilder.bind(pagamentoAConfirmarQueue()).to(fanoutExchangePagamentoAConfirmar());
	}

	@Bean
	public Binding bindPagamentoAConfirmarDLQ() {
		return BindingBuilder.bind(pagamentoAConfirmarDLQ()).to(deadLetterExchangePagmanetoAConfirmar());
	}

	@Bean
	public Binding bindNotificaPagamento() {
		return BindingBuilder.bind(notificaPagamentoQueue()).to(fanoutExchangeNotificaPagamento());
	}

	@Bean
	public Binding bindNotificaPagamentoQueueDLQ() {
		return BindingBuilder.bind(notificaPagamentoQueueDLQ()).to(deadLetterExchangeNotificaPagamento());
	}

	@Bean
	public Queue pagamentoQueue() {
		return QueueBuilder.durable(QueueNames.PAGAMENTOS_PEDIDO)
			.deadLetterExchange(ExchangeNames.PAGAMENTOS_DLX)
			.build();
	}

	@Bean
	public Queue pagamentoDLQ() {
		return QueueBuilder.durable(QueueNames.PAGAMENTOS_PEDIDO_DLQ).build();
	}

	@Bean
	public Queue pagamentoAConfirmarQueue() {
		return QueueBuilder.durable(QueueNames.PAGAMENTOS_A_CONFIRMAR)
			.deadLetterExchange(ExchangeNames.PAGAMENTOS_A_CONFIRMAR_DLX)
			.build();
	}

	@Bean
	public Queue pagamentoAConfirmarDLQ() {
		return QueueBuilder.durable(QueueNames.PAGAMENTOS_A_CONFIRMAR_DLQ).build();
	}

	@Bean
	public Queue notificaPagamentoQueue() {
		return QueueBuilder.durable(QueueNames.NOTIFICAR_PAGAMENTOS)
			.deadLetterExchange(ExchangeNames.NOTIFICAR_PAGAMENTOS_DLX)
			.build();
	}

	@Bean
	public Queue notificaPagamentoQueueDLQ() {
		return QueueBuilder.durable(QueueNames.NOTIFICAR_PAGAMENTOS_DLQ).build();
	}

	@Bean
	public FanoutExchange fanoutExchange() {
		return ExchangeBuilder.fanoutExchange(ExchangeNames.PAGAMENTOS_EX).build();
	}

	@Bean
	public FanoutExchange deadLetterExchangePagamento() {
		return ExchangeBuilder.fanoutExchange(ExchangeNames.PAGAMENTOS_DLX).build();
	}

	@Bean
	public FanoutExchange fanoutExchangePagamentoAConfirmar() {
		return ExchangeBuilder.fanoutExchange(ExchangeNames.PAGAMENTOS_A_CONFIRMAR_EX).build();
	}

	@Bean
	public FanoutExchange deadLetterExchangePagmanetoAConfirmar() {
		return ExchangeBuilder.fanoutExchange(ExchangeNames.PAGAMENTOS_A_CONFIRMAR_DLX).build();
	}

	@Bean
	public FanoutExchange fanoutExchangeNotificaPagamento() {
		return ExchangeBuilder.fanoutExchange(ExchangeNames.NOTIFICAR_PAGAMENTOS_EX).build();
	}

	@Bean
	public FanoutExchange deadLetterExchangeNotificaPagamento() {
		return ExchangeBuilder.fanoutExchange(ExchangeNames.NOTIFICAR_PAGAMENTOS_DLX).build();
	}

}