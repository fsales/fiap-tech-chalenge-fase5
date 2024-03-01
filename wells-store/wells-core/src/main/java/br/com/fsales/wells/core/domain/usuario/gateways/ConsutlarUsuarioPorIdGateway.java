package br.com.fsales.wells.core.domain.usuario.gateways;

import java.util.Optional;

import br.com.fsales.wells.core.domain.usuario.model.Usuario;

public interface ConsutlarUsuarioPorIdGateway {

    Optional<Usuario> find(Long id);
}
