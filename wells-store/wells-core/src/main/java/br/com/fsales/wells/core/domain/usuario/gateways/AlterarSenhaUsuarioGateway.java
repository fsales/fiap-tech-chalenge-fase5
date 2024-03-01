package br.com.fsales.wells.core.domain.usuario.gateways;

import br.com.fsales.wells.core.domain.usuario.model.Usuario;

public interface AlterarSenhaUsuarioGateway {
    Usuario execute(
            final Usuario usuario
    );
}
