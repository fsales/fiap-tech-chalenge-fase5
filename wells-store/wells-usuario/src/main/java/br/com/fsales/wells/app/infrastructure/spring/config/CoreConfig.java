package br.com.fsales.wells.app.infrastructure.spring.config;

import br.com.fsales.wells.app.infrastructure.database.postgres.repository.UsuarioEntityRepository;
import br.com.fsales.wells.app.infrastructure.gateways.usuario.CadastrarUsuarioGatewayImpl;
import br.com.fsales.wells.app.infrastructure.gateways.usuario.ConsultarUsuarioPorUsernameGatewayImpl;
import br.com.fsales.wells.core.domain.usuario.gateways.CadastrarUsuarioGateway;
import br.com.fsales.wells.core.domain.usuario.gateways.ConsultarUsuarioPorUsernameGateway;
import br.com.fsales.wells.core.domain.usuario.usecases.CadastrarUsuarioUseCase;
import br.com.fsales.wells.core.domain.usuario.usecases.impl.CadastrarUsuarioUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreConfig {

    /**
     * @param usuarioEntityRepository
     * @return
     */
    @Bean
    public CadastrarUsuarioGateway cadastrarUsuarioGateway(
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
    public ConsultarUsuarioPorUsernameGateway consultarUsuarioPorUsernameGateway(
            UsuarioEntityRepository usuarioEntityRepository
    ) {
        return new ConsultarUsuarioPorUsernameGatewayImpl(
                usuarioEntityRepository
        );
    }

    /**
     * @param cadastrarUsuarioGateway
     * @return
     */
    @Bean
    public CadastrarUsuarioUseCase cadastrarUsuarioUseCase(
            CadastrarUsuarioGateway cadastrarUsuarioGateway,
            ConsultarUsuarioPorUsernameGateway consultarUsuarioPorUsernameGateway
    ) {

        return new CadastrarUsuarioUseCaseImpl(
                cadastrarUsuarioGateway,
                consultarUsuarioPorUsernameGateway
        );
    }

}
