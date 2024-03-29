package br.com.wells.usuario.app.infrastructure.config.spring.app;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import br.com.wells.usuario.app.infrastructure.database.postgres.repository.RoleEntityRepository;
import br.com.wells.usuario.app.infrastructure.database.postgres.repository.UsuarioEntityRepository;
import br.com.wells.usuario.app.infrastructure.config.spring.security.jwt.JWTToken;
import br.com.wells.usuario.app.infrastructure.config.spring.security.jwt.impl.JWTTokenImpl;
import br.com.wells.usuario.app.infrastructure.config.spring.security.user.UsuarioCustomDetailsService;
import br.com.wells.usuario.app.infrastructure.config.spring.security.user.impl.UsuarioCustomDetailsServiceImpl;
import br.com.wells.core.domain.usuario.gateways.*;
import br.com.wells.core.domain.usuario.usecases.*;
import br.com.wells.core.domain.usuario.usecases.impl.*;
import br.com.wells.usuario.app.infrastructure.gateways.usuario.AlterarSenhaUsuarioGatewayImpl;
import br.com.wells.usuario.app.infrastructure.gateways.usuario.CadastrarUsuarioGatewayImpl;
import br.com.wells.usuario.app.infrastructure.gateways.usuario.ConsultarRolePorNomeGatewayImpl;
import br.com.wells.usuario.app.infrastructure.gateways.usuario.ConsultarUsuarioPorUsernameGatewayImpl;
import br.com.wells.usuario.app.infrastructure.gateways.usuario.ConsutlarTodosUsuarioGatewayImpl;
import br.com.wells.usuario.app.infrastructure.gateways.usuario.ConsutlarUsuarioPorIdGatewayImpl;
import org.springframework.beans.factory.annotation.Value;
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
	 * @param publicKey
	 * @param privateKey
	 * @return
	 */
	@Bean
	JWTToken jwtToken(@Value("${app.api.security.jwt.public-key}") RSAPublicKey publicKey,
			@Value("${app.api.security.jwt.private-key}") RSAPrivateKey privateKey,
			WellsUsuarioAppProperties wellsUsuarioAppProperties) {
		return new JWTTokenImpl(publicKey, privateKey, wellsUsuarioAppProperties);
	}

}
