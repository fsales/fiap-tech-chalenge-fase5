package br.com.fsales.wells.core.domain.usuario.gateways;

import br.com.fsales.wells.core.domain.usuario.model.Usuario;

public interface CadastrarUsuarioGateway {

    Usuario execute(Usuario usuario);

}
