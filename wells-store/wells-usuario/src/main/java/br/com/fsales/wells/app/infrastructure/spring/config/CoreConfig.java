package br.com.fsales.wells.app.infrastructure.spring.config;

import br.com.fsales.wells.app.infrastructure.database.postgres.adpter.CadastrarUsuarioAdapter;
import br.com.fsales.wells.app.infrastructure.database.postgres.adpter.ConsultarUsuarioPorUsernameAdapter;
import br.com.fsales.wells.app.infrastructure.database.postgres.repository.UsuarioEntityRepository;
import br.com.fsales.wells.app.usecase.usuario.CadastrarUsuarioUseCase;
import br.com.fsales.wells.app.usecase.usuario.CadastrarUsuarioUseCaseImpl;
import br.com.fsales.wells.core.domain.usuario.repository.CadastrarUsuarioRepository;
import br.com.fsales.wells.core.domain.usuario.repository.ConsultarUsuarioPorUsernameRepository;
import br.com.fsales.wells.core.domain.usuario.service.CadastrarUsuarioService;
import br.com.fsales.wells.core.domain.usuario.service.impl.CadastrarUsuarioServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreConfig {

    /**
     * @param usuarioEntityRepository
     * @return
     */
    @Bean
    public CadastrarUsuarioRepository cadastrarUsuarioRepository(
            UsuarioEntityRepository usuarioEntityRepository
    ) {
        return new CadastrarUsuarioAdapter(
                usuarioEntityRepository
        );
    }

    /**
     * @param usuarioEntityRepository
     * @return
     */
    @Bean
    public ConsultarUsuarioPorUsernameRepository consultarUsuarioPorUsernameRepository(
            UsuarioEntityRepository usuarioEntityRepository
    ) {
        return new ConsultarUsuarioPorUsernameAdapter(
                usuarioEntityRepository
        );
    }

    /**
     * @param cadastrarUsuarioRepository
     * @return
     */
    @Bean
    public CadastrarUsuarioService cadastrarUsuarioService(
            CadastrarUsuarioRepository cadastrarUsuarioRepository,
            ConsultarUsuarioPorUsernameRepository consultarUsuarioPorUsernameRepository
    ) {

        return new CadastrarUsuarioServiceImpl(
                cadastrarUsuarioRepository,
                consultarUsuarioPorUsernameRepository
        );
    }

    /**
     * @param cadastrarUsuarioService
     * @return
     */
    @Bean
    public CadastrarUsuarioUseCase cadastrarUsuarioUseCase(
            CadastrarUsuarioService cadastrarUsuarioService
    ) {
        return new CadastrarUsuarioUseCaseImpl(
                cadastrarUsuarioService
        );
    }
}
