package br.com.fsales.wells.core.domain.usuario.repository;

import br.com.fsales.wells.core.domain.usuario.model.Usuario;

public interface CadastrarUsuarioRepository {

    Usuario execute(Usuario usuario);

}
