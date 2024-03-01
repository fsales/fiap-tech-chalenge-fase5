package br.com.fsales.wells.core.domain.usuario.gateways;

import br.com.fsales.wells.core.domain.usuario.model.Usuario;

import java.util.Optional;

public interface ConsutlarUsuarioPorIdGateway {

    Optional<Usuario> find(Long id);
}
