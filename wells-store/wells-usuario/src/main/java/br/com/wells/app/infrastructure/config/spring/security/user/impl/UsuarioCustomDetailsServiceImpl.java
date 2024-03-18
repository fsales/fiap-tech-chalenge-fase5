package br.com.wells.app.infrastructure.config.spring.security.user.impl;

import br.com.wells.app.infrastructure.config.spring.security.user.UsuarioCustomDetailsService;
import br.com.wells.app.infrastructure.config.spring.security.user.UsuarioCustomDetails;
import br.com.wells.core.domain.usuario.usecases.ConsultarUsuarioPorUsernameUseCase;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UsuarioCustomDetailsServiceImpl implements UsuarioCustomDetailsService {

	private final ConsultarUsuarioPorUsernameUseCase consultarUsuarioPorUsernameUseCase;

	public UsuarioCustomDetailsServiceImpl(ConsultarUsuarioPorUsernameUseCase consultarUsuarioPorUsernameUseCase) {
		this.consultarUsuarioPorUsernameUseCase = consultarUsuarioPorUsernameUseCase;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		var usuario = consultarUsuarioPorUsernameUseCase.find(username);

		return new UsuarioCustomDetails(usuario.username(), usuario.senha(),
				usuario.roles().stream().map(role -> new SimpleGrantedAuthority("ROLE_".concat(role.nome()))).toList(),
				usuario.id());
	}

}
