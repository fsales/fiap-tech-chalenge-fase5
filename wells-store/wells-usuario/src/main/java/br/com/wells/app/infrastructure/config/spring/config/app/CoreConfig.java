package br.com.wells.app.infrastructure.config.spring.config.app;

import br.com.wells.app.infrastructure.database.postgres.repository.RoleEntityRepository;
import br.com.wells.app.infrastructure.database.postgres.repository.UsuarioEntityRepository;
import br.com.wells.app.infrastructure.gateways.usuario.*;
import br.com.wells.app.infrastructure.config.spring.security.jwt.JWTToken;
import br.com.wells.app.infrastructure.config.spring.security.jwt.impl.JWTTokenImpl;
import br.com.wells.app.infrastructure.config.spring.security.user.UsuarioCustomDetailsService;
import br.com.wells.app.infrastructure.config.spring.security.user.impl.UsuarioCustomDetailsServiceImpl;
import br.com.wells.core.domain.usuario.gateways.*;
import br.com.wells.core.domain.usuario.usecases.*;
import br.com.wells.core.domain.usuario.usecases.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreConfig {

	/**
	 * @param usuarioEntityRepository
	 * @param roleEntityRepository
	 * @return
	 */
	@Bean
	CadastrarUsuarioGateway cadastrarUsuarioGateway(UsuarioEntityRepository usuarioEntityRepository,
			RoleEntityRepository roleEntityRepository) {
		return new CadastrarUsuarioGatewayImpl(usuarioEntityRepository, roleEntityRepository);
	}

	/**
	 * @param usuarioEntityRepository
	 * @return
	 */
	@Bean
	ConsultarUsuarioPorUsernameGateway consultarUsuarioPorUsernameGateway(
			UsuarioEntityRepository usuarioEntityRepository) {
		return new ConsultarUsuarioPorUsernameGatewayImpl(usuarioEntityRepository);
	}

	/**
	 * @param usuarioEntityRepository
	 * @return
	 */
	@Bean
	ConsutlarUsuarioPorIdGateway consutlarUsuarioPorIdGateway(UsuarioEntityRepository usuarioEntityRepository) {
		return new ConsutlarUsuarioPorIdGatewayImpl(usuarioEntityRepository);
	}

	/**
	 * @param usuarioEntityRepository
	 * @return
	 */
	@Bean
	AlterarSenhaUsuarioGateway alterarSenhaUsuarioGateway(UsuarioEntityRepository usuarioEntityRepository) {
		return new AlterarSenhaUsuarioGatewayImpl(usuarioEntityRepository);
	}

	/**
	 * @param usuarioEntityRepository
	 * @return
	 */
	@Bean
	ConsutlarTodosUsuarioGateway consutlarTodosUsuarioGateway(UsuarioEntityRepository usuarioEntityRepository) {
		return new ConsutlarTodosUsuarioGatewayImpl(usuarioEntityRepository);
	}

	@Bean
	ConsultarRolePorNomeGateway consultarRolePorNomeGateway(RoleEntityRepository roleEntityRepository) {
		return new ConsultarRolePorNomeGatewayImpl(roleEntityRepository);
	}

	/**
	 * @param cadastrarUsuarioGateway
	 * @return
	 */
	@Bean
	CadastrarUsuarioUseCase cadastrarUsuarioUseCase(CadastrarUsuarioGateway cadastrarUsuarioGateway,
			ConsultarUsuarioPorUsernameGateway consultarUsuarioPorUsernameGateway,
			ConsultarRolePorNomeGateway consultarRolePorNomeGateway) {

		return new CadastrarUsuarioUseCaseImpl(cadastrarUsuarioGateway, consultarUsuarioPorUsernameGateway,
				consultarRolePorNomeGateway);
	}

	/**
	 * @param consutlarUsuarioPorIdGateway
	 * @return
	 */
	@Bean
	ConsutlarUsuarioPorIdUseCase consutlarUsuarioPorIdUseCase(
			ConsutlarUsuarioPorIdGateway consutlarUsuarioPorIdGateway) {
		return new ConsutlarUsuarioPorIdUseCaseImpl(consutlarUsuarioPorIdGateway);
	}

	/**
	 * @param consultarUsuarioPorUsernameGateway
	 * @return
	 */
	@Bean
	ConsultarUsuarioPorUsernameUseCase consultarUsuarioPorUsernameUseCase(
			ConsultarUsuarioPorUsernameGateway consultarUsuarioPorUsernameGateway) {
		return new ConsultarUsuarioPorUsernameUseCaseImpl(consultarUsuarioPorUsernameGateway);
	}

	/**
	 * @param alterarSenhaUsuarioGateway
	 * @param consutlarUsuarioPorIdUseCase
	 * @return
	 */
	@Bean
	AlterarSenhaUsuarioUseCase alterarSenhaUsuarioUseCase(AlterarSenhaUsuarioGateway alterarSenhaUsuarioGateway,
			ConsutlarUsuarioPorIdUseCase consutlarUsuarioPorIdUseCase) {
		return new AlterarSenhaUsuarioUseCaseImpl(alterarSenhaUsuarioGateway, consutlarUsuarioPorIdUseCase);
	}

	/**
	 * @param consutlarTodosUsuarioGateway
	 * @return
	 */
	@Bean
	ConsutlarTodosUsuarioUseCase consutlarTodosUsuarioUseCase(
			ConsutlarTodosUsuarioGateway consutlarTodosUsuarioGateway) {
		return new ConsutlarTodosUsuarioUseCaseImpl(consutlarTodosUsuarioGateway);
	}

	/**
	 * @param consultarUsuarioPorUsernameUseCase
	 * @return
	 */
	@Bean
	UsuarioCustomDetailsService usuarioCustomDetailsUserCase(
			ConsultarUsuarioPorUsernameUseCase consultarUsuarioPorUsernameUseCase) {
		return new UsuarioCustomDetailsServiceImpl(consultarUsuarioPorUsernameUseCase);
	}

	/**
	 * @param wellsUsuarioAppProperties
	 * @return
	 */
	@Bean
	JWTToken jwtToken(WellsUsuarioAppProperties wellsUsuarioAppProperties) {
		return new JWTTokenImpl(wellsUsuarioAppProperties);
	}

}
