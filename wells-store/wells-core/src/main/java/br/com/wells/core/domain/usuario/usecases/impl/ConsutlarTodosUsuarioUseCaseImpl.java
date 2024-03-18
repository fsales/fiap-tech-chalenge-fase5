package br.com.wells.core.domain.usuario.usecases.impl;

import br.com.wells.core.domain.page.Pagina;
import br.com.wells.core.domain.usuario.gateways.ConsutlarTodosUsuarioGateway;
import br.com.wells.core.domain.usuario.model.Usuario;
import br.com.wells.core.domain.usuario.usecases.ConsutlarTodosUsuarioUseCase;

public class ConsutlarTodosUsuarioUseCaseImpl implements ConsutlarTodosUsuarioUseCase {

	private final ConsutlarTodosUsuarioGateway consutlarTodosUsuarioGateway;

	public ConsutlarTodosUsuarioUseCaseImpl(ConsutlarTodosUsuarioGateway consutlarTodosUsuarioGateway) {
		this.consutlarTodosUsuarioGateway = consutlarTodosUsuarioGateway;
	}

	@Override
	public Pagina<Usuario> find(int pageNumber, int pageSize) {
		return consutlarTodosUsuarioGateway.find(pageNumber, pageSize);
	}

}
