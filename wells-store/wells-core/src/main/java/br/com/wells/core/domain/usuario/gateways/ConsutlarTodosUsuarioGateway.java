package br.com.wells.core.domain.usuario.gateways;

import br.com.wells.core.domain.page.Pagina;
import br.com.wells.core.domain.usuario.model.Usuario;

public interface ConsutlarTodosUsuarioGateway {

    Pagina<Usuario> find(int pageNumber, int pageSize);
}
