package br.com.wells.app.usecases.security.impl;

import br.com.wells.app.infrastructure.spring.config.security.UsuarioCustomDetails;
import br.com.wells.app.usecases.security.RoleConverter;
import br.com.wells.app.usecases.security.UsuarioCustomDetailsUserCase;
import br.com.wells.core.domain.usuario.usecases.ConsultarUsuarioPorUsernameUseCase;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public class UsuarioCustomDetailsUserCaseImpl implements UsuarioCustomDetailsUserCase, UserDetailsService {

    private final ConsultarUsuarioPorUsernameUseCase consultarUsuarioPorUsernameUseCase;

    public UsuarioCustomDetailsUserCaseImpl(
            ConsultarUsuarioPorUsernameUseCase consultarUsuarioPorUsernameUseCase
    ) {
        this.consultarUsuarioPorUsernameUseCase = consultarUsuarioPorUsernameUseCase;
    }

    @Override
    public UserDetails loadUserByUsername(
            String username
    ) {
        var usuario = consultarUsuarioPorUsernameUseCase.find(username);

        return new UsuarioCustomDetails(
                usuario.username(),
                usuario.senha(),
                RoleConverter.convertToAuthorities(
                        List.of(usuario.role())
                )
        );
    }
}
