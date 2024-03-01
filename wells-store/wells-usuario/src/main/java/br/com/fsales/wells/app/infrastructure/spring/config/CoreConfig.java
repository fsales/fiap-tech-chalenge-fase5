package br.com.fsales.wells.app.infrastructure.spring.config;

import br.com.fsales.wells.app.infrastructure.database.postgres.repository.UsuarioEntityRepository;
import br.com.fsales.wells.app.infrastructure.gateways.usuario.CadastrarUsuarioGatewayImpl;
import br.com.fsales.wells.app.infrastructure.gateways.usuario.ConsultarUsuarioPorUsernameGatewayImpl;
import br.com.fsales.wells.app.infrastructure.gateways.usuario.ConsutlarUsuarioPorIdGatewayImpl;
import br.com.fsales.wells.core.domain.usuario.gateways.CadastrarUsuarioGateway;
import br.com.fsales.wells.core.domain.usuario.gateways.ConsultarUsuarioPorUsernameGateway;
import br.com.fsales.wells.core.domain.usuario.gateways.ConsutlarUsuarioPorIdGateway;
import br.com.fsales.wells.core.domain.usuario.usecases.CadastrarUsuarioUseCase;
import br.com.fsales.wells.core.domain.usuario.usecases.ConsultarUsuarioPorUsernameUseCase;
import br.com.fsales.wells.core.domain.usuario.usecases.ConsutlarUsuarioPorIdUseCase;
import br.com.fsales.wells.core.domain.usuario.usecases.impl.CadastrarUsuarioUseCaseImpl;
import br.com.fsales.wells.core.domain.usuario.usecases.impl.ConsultarUsuarioPorUsernameUseCaseImpl;
import br.com.fsales.wells.core.domain.usuario.usecases.impl.ConsutlarUsuarioPorIdUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreConfig {

    /**
     * @param usuarioEntityRepository
     * @return
     */
    @Bean
    CadastrarUsuarioGateway cadastrarUsuarioGateway(
            UsuarioEntityRepository usuarioEntityRepository
    ) {
        return new CadastrarUsuarioGatewayImpl(
                usuarioEntityRepository
        );
    }

    /**
     * @param usuarioEntityRepository
     * @return
     */
    @Bean
    ConsultarUsuarioPorUsernameGateway consultarUsuarioPorUsernameGateway(
            UsuarioEntityRepository usuarioEntityRepository
    ) {
        return new ConsultarUsuarioPorUsernameGatewayImpl(
                usuarioEntityRepository
        );
    }

    /**
     * @param usuarioEntityRepository
     * @return
     */
    @Bean
    ConsutlarUsuarioPorIdGateway consutlarUsuarioPorIdGateway(
            UsuarioEntityRepository usuarioEntityRepository
    ) {
        return new ConsutlarUsuarioPorIdGatewayImpl(
                usuarioEntityRepository
        );
    }

    /**
     * @param cadastrarUsuarioGateway
     * @return
     */
    @Bean
    CadastrarUsuarioUseCase cadastrarUsuarioUseCase(
            CadastrarUsuarioGateway cadastrarUsuarioGateway,
            ConsultarUsuarioPorUsernameGateway consultarUsuarioPorUsernameGateway
    ) {

        return new CadastrarUsuarioUseCaseImpl(
                cadastrarUsuarioGateway,
                consultarUsuarioPorUsernameGateway
        );
    }

    @Bean
    ConsutlarUsuarioPorIdUseCase consutlarUsuarioPorIdUseCase(
            ConsutlarUsuarioPorIdGateway consutlarUsuarioPorIdGateway
    ) {
        return new ConsutlarUsuarioPorIdUseCaseImpl(
                consutlarUsuarioPorIdGateway
        );
    }

    @Bean
    ConsultarUsuarioPorUsernameUseCase consultarUsuarioPorUsernameUseCase(
            ConsultarUsuarioPorUsernameGateway consultarUsuarioPorUsernameGateway
    ) {
        return new ConsultarUsuarioPorUsernameUseCaseImpl(
                consultarUsuarioPorUsernameGateway
        );
    }

}
