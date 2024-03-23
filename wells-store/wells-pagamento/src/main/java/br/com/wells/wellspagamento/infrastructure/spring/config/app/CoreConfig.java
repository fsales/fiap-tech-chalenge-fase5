package br.com.wells.wellspagamento.infrastructure.spring.config.app;

import br.com.wells.core.domain.pagamento.gateways.AlteraStatusPagamentoGateway;
import br.com.wells.core.domain.pagamento.gateways.AtualizarPagamentoGateway;
import br.com.wells.core.domain.pagamento.gateways.ConfirmarPagamentoGateway;
import br.com.wells.core.domain.pagamento.gateways.ConsultarPagamentoPorIdGateway;
import br.com.wells.core.domain.pagamento.gateways.ConsultarTodosPagamentoGateway;
import br.com.wells.core.domain.pagamento.gateways.CriarPagamentoGateway;
import br.com.wells.core.domain.pagamento.gateways.ExcluirPagamentoGateway;
import br.com.wells.core.domain.pagamento.usecases.AlteraStatusPagamentoUseCase;
import br.com.wells.core.domain.pagamento.usecases.AtualizarPagamentoUseCase;
import br.com.wells.core.domain.pagamento.usecases.ConfirmarPagamentoUseCase;
import br.com.wells.core.domain.pagamento.usecases.ConsultarPagamentoPorIdUseCase;
import br.com.wells.core.domain.pagamento.usecases.ConsultarTodosPagamentoUseCase;
import br.com.wells.core.domain.pagamento.usecases.CriarPagamentoUseCase;
import br.com.wells.core.domain.pagamento.usecases.ExcluirPagamentoUseCase;
import br.com.wells.core.domain.pagamento.usecases.impl.AlteraStatusPagamentoUseCaseImpl;
import br.com.wells.core.domain.pagamento.usecases.impl.AtualizarPagamentoUseCaseImpl;
import br.com.wells.core.domain.pagamento.usecases.impl.ConfirmarPagamentoUseCaseImpl;
import br.com.wells.core.domain.pagamento.usecases.impl.ConsultarPagamentoPorIdUseCaseImpl;
import br.com.wells.core.domain.pagamento.usecases.impl.ConsultarTodosPagamentoUseCaseImpl;
import br.com.wells.core.domain.pagamento.usecases.impl.CriarPagamentoUseCaseImpl;
import br.com.wells.core.domain.pagamento.usecases.impl.ExcluirPagamentoUseCaseImpl;
import br.com.wells.wellspagamento.infrastructure.database.postgres.repository.PagamentoEntityRepository;
import br.com.wells.wellspagamento.infrastructure.gateways.pagamento.AlteraStatusPagamentoGatewayImpl;
import br.com.wells.wellspagamento.infrastructure.gateways.pagamento.AtualizarPagamentoGatewayImpl;
import br.com.wells.wellspagamento.infrastructure.gateways.pagamento.ConfirmarPagamentoGatewayImpl;
import br.com.wells.wellspagamento.infrastructure.gateways.pagamento.ConsultarPagamentoPorIdGatewayImpl;
import br.com.wells.wellspagamento.infrastructure.gateways.pagamento.ConsultarTodosPagamentoGatewayImpl;
import br.com.wells.wellspagamento.infrastructure.gateways.pagamento.CriarPagamentoGatewayImpl;
import br.com.wells.wellspagamento.infrastructure.gateways.pagamento.ExcluirPagamentoGatewayImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreConfig {

	@Bean
	AlteraStatusPagamentoUseCase alteraStatusPagamentoUseCase(
			AlteraStatusPagamentoGateway alteraStatusPagamentoGateway) {
		return new AlteraStatusPagamentoUseCaseImpl(alteraStatusPagamentoGateway);
	}

	@Bean
	AtualizarPagamentoUseCase atualizarPagamentoUseCase(AtualizarPagamentoGateway atualizarPagamentoGateway) {
		return new AtualizarPagamentoUseCaseImpl(atualizarPagamentoGateway);
	}

	@Bean
	ConfirmarPagamentoUseCase confirmarPagamentoUseCase(ConfirmarPagamentoGateway confirmarPagamentoGateway) {
		return new ConfirmarPagamentoUseCaseImpl(confirmarPagamentoGateway);
	}

	@Bean
	ConsultarPagamentoPorIdUseCase consultarPagamentoPorIdUseCase(
			ConsultarPagamentoPorIdGateway consultarPagamentoPorIdGateway) {
		return new ConsultarPagamentoPorIdUseCaseImpl(consultarPagamentoPorIdGateway);
	}

	@Bean
	ConsultarTodosPagamentoUseCase consultarTodosPagamentoUseCase(
			ConsultarTodosPagamentoGateway consultarTodosPagamentoGateway) {
		return new ConsultarTodosPagamentoUseCaseImpl(consultarTodosPagamentoGateway);
	}

	@Bean
	CriarPagamentoUseCase criarPagamentoUseCase(CriarPagamentoGateway criarPagamentoGateway) {
		return new CriarPagamentoUseCaseImpl(criarPagamentoGateway);
	}

	@Bean
	ExcluirPagamentoUseCase excluirPagamentoUseCase(ExcluirPagamentoGateway excluirPagamentoGateway) {
		return new ExcluirPagamentoUseCaseImpl(excluirPagamentoGateway);
	}

	/** gateway */
	@Bean
	AlteraStatusPagamentoGateway alteraStatusPagamentoGateway(PagamentoEntityRepository pagamentoEntityRepository) {
		return new AlteraStatusPagamentoGatewayImpl(pagamentoEntityRepository);

	}

	@Bean
	AtualizarPagamentoGateway atualizarPagamentoGateway(PagamentoEntityRepository pagamentoEntityRepository) {
		return new AtualizarPagamentoGatewayImpl(pagamentoEntityRepository);
	}

	@Bean
	ConfirmarPagamentoGateway confirmarPagamentoGateway(PagamentoEntityRepository pagamentoEntityRepository) {
		return new ConfirmarPagamentoGatewayImpl(pagamentoEntityRepository);

	}

	@Bean
	ConsultarPagamentoPorIdGateway consultarPagamentoPorIdGateway(PagamentoEntityRepository pagamentoEntityRepository) {
		return new ConsultarPagamentoPorIdGatewayImpl(pagamentoEntityRepository);
	}

	@Bean
	ConsultarTodosPagamentoGateway consultarTodosPagamentoGateway(PagamentoEntityRepository pagamentoEntityRepository) {
		return new ConsultarTodosPagamentoGatewayImpl(pagamentoEntityRepository);
	}

	@Bean
	CriarPagamentoGateway criarPagamentoGateway(PagamentoEntityRepository pagamentoEntityRepository) {
		return new CriarPagamentoGatewayImpl(pagamentoEntityRepository);
	}

	@Bean
	ExcluirPagamentoGateway excluirPagamentoGateway(PagamentoEntityRepository pagamentoEntityRepository) {
		return new ExcluirPagamentoGatewayImpl(pagamentoEntityRepository);
	}

}
