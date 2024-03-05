package br.com.wells.app.infrastructure.gateways.usuario;

import br.com.wells.app.infrastructure.database.postgres.repository.RoleEntityRepository;
import br.com.wells.app.infrastructure.gateways.usuario.mapper.RolerEntityMapper;
import br.com.wells.core.domain.usuario.exception.UsuarioInvalidoException;
import br.com.wells.core.domain.usuario.gateways.ConsultarRolePorNomeGateway;
import br.com.wells.core.domain.usuario.model.Role;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ConsultarRolePorNomeGatewayImpl implements ConsultarRolePorNomeGateway {
    private final RoleEntityRepository roleEntityRepository;

    public ConsultarRolePorNomeGatewayImpl(RoleEntityRepository roleEntityRepository) {
        this.roleEntityRepository = roleEntityRepository;
    }

    @Override
    public Optional<Set<Role>> find(Set<String> nomes) {
        return roleEntityRepository.findByNomesIgnoreCaseSafe(nomes)
                .map(entities -> entities.stream()
                        .map(RolerEntityMapper::convertToRole)
                        .collect(Collectors.toSet()))
                .or(() -> {
                    throw new UsuarioInvalidoException("Role não existe.");
                });
    }
}
