package br.com.wells.app.infrastructure.gateways.usuario;

import br.com.wells.app.infrastructure.database.postgres.repository.UsuarioEntityRepository;
import br.com.wells.app.infrastructure.gateways.usuario.mapper.UsuarioEntityMapper;
import br.com.wells.core.domain.usuario.gateways.CadastrarUsuarioGateway;
import br.com.wells.core.domain.usuario.model.Usuario;

public class CadastrarUsuarioGatewayImpl implements CadastrarUsuarioGateway {

    private final UsuarioEntityRepository usuarioEntityRepository;

    public CadastrarUsuarioGatewayImpl(UsuarioEntityRepository usuarioEntityRepository) {
        this.usuarioEntityRepository = usuarioEntityRepository;
    }

    @Override
    public Usuario execute(Usuario usuario) {

        var usuarioSalvar = UsuarioEntityMapper.convertToUsuarioEntity(usuario);

        var usuarioEntity = usuarioEntityRepository.save(usuarioSalvar);

        return UsuarioEntityMapper.convertToUsuario(usuarioEntity);
    }
}
