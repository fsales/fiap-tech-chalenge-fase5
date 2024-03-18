package br.com.wells.core.domain.usuario.gateways;

import br.com.wells.core.domain.usuario.model.Role;

import java.util.Optional;
import java.util.Set;

public interface ConsultarRolePorNomeGateway {

	Optional<Set<Role>> find(Set<String> nomes);

}
