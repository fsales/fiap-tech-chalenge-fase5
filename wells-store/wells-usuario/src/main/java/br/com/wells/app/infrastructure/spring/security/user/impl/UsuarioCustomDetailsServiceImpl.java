package br.com.wells.app.infrastructure.spring.security.user.impl;

import br.com.wells.app.infrastructure.spring.security.user.UsuarioCustomDetailsService;
import br.com.wells.core.domain.usuario.usecases.ConsultarUsuarioPorUsernameUseCase;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

public class UsuarioCustomDetailsServiceImpl implements UsuarioCustomDetailsService {

    private final ConsultarUsuarioPorUsernameUseCase consultarUsuarioPorUsernameUseCase;

    public UsuarioCustomDetailsServiceImpl(
            ConsultarUsuarioPorUsernameUseCase consultarUsuarioPorUsernameUseCase
    ) {
        this.consultarUsuarioPorUsernameUseCase = consultarUsuarioPorUsernameUseCase;
    }

    @Override
    public UserDetails loadUserByUsername(
            String username
    ) {
        var usuario = consultarUsuarioPorUsernameUseCase.find(username);

        return new br.com.wells.app.infrastructure.spring.security.user.UsuarioCustomDetails(
                usuario.username(),
                usuario.senha(),
                AuthorityUtils.createAuthorityList(
                        usuario.role().name()
                )
        );
    }
}