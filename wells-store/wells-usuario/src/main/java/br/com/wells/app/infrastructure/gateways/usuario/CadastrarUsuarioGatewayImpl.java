package br.com.wells.app.infrastructure.gateways.usuario;

import br.com.wells.app.infrastructure.database.postgres.entity.RoleEntity;
import br.com.wells.app.infrastructure.database.postgres.repository.RoleEntityRepository;
import br.com.wells.app.infrastructure.database.postgres.repository.UsuarioEntityRepository;
import br.com.wells.app.infrastructure.gateways.usuario.mapper.UsuarioEntityMapper;
import br.com.wells.core.domain.usuario.gateways.CadastrarUsuarioGateway;
import br.com.wells.core.domain.usuario.model.Usuario;

import java.util.HashSet;
import java.util.stream.Collectors;

public class CadastrarUsuarioGatewayImpl implements CadastrarUsuarioGateway {

    private final UsuarioEntityRepository usuarioEntityRepository;

    private final RoleEntityRepository roleEntityRepository;

    public CadastrarUsuarioGatewayImpl(
            UsuarioEntityRepository usuarioEntityRepository,
            RoleEntityRepository roleEntityRepository
    ) {
        this.usuarioEntityRepository = usuarioEntityRepository;
        this.roleEntityRepository = roleEntityRepository;
    }

    @Override
    public Usuario execute(Usuario usuario) {

        var usuarioSalvar = UsuarioEntityMapper.convertToUsuarioEntity(usuario);

        var rolesEntities = new HashSet<>(roleEntityRepository.findAllById(
                usuarioSalvar.getRoles().stream().map(RoleEntity::getId
                ).collect(Collectors.toSet())));

        usuarioSalvar.setRoles(rolesEntities);

        var usuarioEntity = usuarioEntityRepository.save(usuarioSalvar);

        return UsuarioEntityMapper.convertToUsuario(usuarioEntity);
    }
}
