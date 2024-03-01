package br.com.wells.core.domain.usuario.usecases;

import br.com.wells.core.domain.page.Pagina;
import br.com.wells.core.domain.usuario.model.Usuario;

public interface ConsutlarTodosUsuarioUseCase {
    Pagina<Usuario> find(int pageNumber, int pageSize);
}
