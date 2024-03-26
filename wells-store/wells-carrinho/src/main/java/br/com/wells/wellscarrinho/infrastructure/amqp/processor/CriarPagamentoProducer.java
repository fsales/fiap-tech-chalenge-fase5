package br.com.wells.wellscarrinho.infrastructure.amqp.processor;

import br.com.wells.core.domain.pagamento.model.Pagamento;
import br.com.wells.wellscarrinho.infrastructure.amqp.constants.ExchangeNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class CriarPagamentoProducer {

	private final RabbitTemplate rabbitTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(CriarPagamentoProducer.class);

	public CriarPagamentoProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public void produceCriarPagamento(Pagamento pagamento) {
		try {
			rabbitTemplate.convertAndSend(ExchangeNames.PAGAMENTOS_EX, "", pagamento);
			LOGGER.info("Pagamento enviado para o exchange: {}", pagamento);
		}
		catch (Exception e) {
			LOGGER.error("Erro ao enviar pagamento", e);
		}
	}

}