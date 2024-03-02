package br.com.wells.app.infrastructure.spring.config.app;

import br.com.wells.app.infrastructure.database.postgres.repository.UsuarioEntityRepository;
import br.com.wells.app.infrastructure.gateways.usuario.*;
import br.com.wells.app.usecases.security.TokenUserCase;
import br.com.wells.app.usecases.security.UsuarioCustomDetailsUserCase;
import br.com.wells.app.usecases.security.impl.TokenUserCaseImpl;
import br.com.wells.app.usecases.security.impl.UsuarioCustomDetailsUserCaseImpl;
import br.com.wells.core.domain.usuario.gateways.*;
import br.com.wells.core.domain.usuario.usecases.*;
import br.com.wells.core.domain.usuario.usecases.impl.*;
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
     * @param usuarioEntityRepository
     * @return
     */
    @Bean
    AlterarSenhaUsuarioGateway alterarSenhaUsuarioGateway(
            UsuarioEntityRepository usuarioEntityRepository
    ) {
        return new AlterarSenhaUsuarioGatewayImpl(
                usuarioEntityRepository
        );
    }

    /**
     * @param usuarioEntityRepository
     * @return
     */
    @Bean
    ConsutlarTodosUsuarioGateway consutlarTodosUsuarioGateway(
            UsuarioEntityRepository usuarioEntityRepository
    ) {
        return new ConsutlarTodosUsuarioGatewayImpl(
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

    /**
     * @param consutlarUsuarioPorIdGateway
     * @return
     */
    @Bean
    ConsutlarUsuarioPorIdUseCase consutlarUsuarioPorIdUseCase(
            ConsutlarUsuarioPorIdGateway consutlarUsuarioPorIdGateway
    ) {
        return new ConsutlarUsuarioPorIdUseCaseImpl(
                consutlarUsuarioPorIdGateway
        );
    }

    /**
     * @param consultarUsuarioPorUsernameGateway
     * @return
     */
    @Bean
    ConsultarUsuarioPorUsernameUseCase consultarUsuarioPorUsernameUseCase(
            ConsultarUsuarioPorUsernameGateway consultarUsuarioPorUsernameGateway
    ) {
        return new ConsultarUsuarioPorUsernameUseCaseImpl(
                consultarUsuarioPorUsernameGateway
        );
    }

    /**
     * @param alterarSenhaUsuarioGateway
     * @param consutlarUsuarioPorIdUseCase
     * @return
     */
    @Bean
    AlterarSenhaUsuarioUseCase alterarSenhaUsuarioUseCase(
            AlterarSenhaUsuarioGateway alterarSenhaUsuarioGateway,
            ConsutlarUsuarioPorIdUseCase consutlarUsuarioPorIdUseCase
    ) {
        return new AlterarSenhaUsuarioUseCaseImpl(
                alterarSenhaUsuarioGateway,
                consutlarUsuarioPorIdUseCase
        );
    }

    /**
     * @param consutlarTodosUsuarioGateway
     * @return
     */
    @Bean
    ConsutlarTodosUsuarioUseCase consutlarTodosUsuarioUseCase(
            ConsutlarTodosUsuarioGateway consutlarTodosUsuarioGateway
    ) {
        return new ConsutlarTodosUsuarioUseCaseImpl(
                consutlarTodosUsuarioGateway
        );
    }

    /**
     * @param consultarUsuarioPorUsernameUseCase
     * @return
     */
    @Bean
    UsuarioCustomDetailsUserCase usuarioCustomDetailsUserCase(
            ConsultarUsuarioPorUsernameUseCase consultarUsuarioPorUsernameUseCase
    ){
        return new UsuarioCustomDetailsUserCaseImpl(
                consultarUsuarioPorUsernameUseCase
        );
    }

    /**
     * @param wellsUsuarioAppProperties
     * @return
     */
    @Bean
    TokenUserCase tokenUserCase(
            WellsUsuarioAppProperties wellsUsuarioAppProperties
    ){
        return new TokenUserCaseImpl(
                wellsUsuarioAppProperties
        );
    }
}
