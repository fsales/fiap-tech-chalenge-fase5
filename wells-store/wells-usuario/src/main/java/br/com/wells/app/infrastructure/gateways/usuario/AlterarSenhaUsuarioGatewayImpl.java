package br.com.wells.app.infrastructure.gateways.usuario;

import br.com.wells.app.infrastructure.database.postgres.repository.UsuarioEntityRepository;
import br.com.wells.app.infrastructure.gateways.usuario.mapper.UsuarioEntityMapper;
import br.com.wells.core.domain.usuario.gateways.AlterarSenhaUsuarioGateway;
import br.com.wells.core.domain.usuario.model.Usuario;

public class AlterarSenhaUsuarioGatewayImpl implements AlterarSenhaUsuarioGateway {

    private final UsuarioEntityRepository usuarioEntityRepository;

    public AlterarSenhaUsuarioGatewayImpl(
            UsuarioEntityRepository usuarioEntityRepository
    ) {
        this.usuarioEntityRepository = usuarioEntityRepository;
    }

    @Override
    public Usuario execute(Usuario usuario) {
        if (usuario.id() == null)
            throw new IllegalArgumentException("O ID do usuário e obrigatorio");

        var usuarioSalvo = this.usuarioEntityRepository.getReferenceById(usuario.id());

        usuarioSalvo.setSenha(usuario.senha());

        // atualizando
        this.usuarioEntityRepository.save(usuarioSalvo);

        return UsuarioEntityMapper.convertToUsuario(usuarioSalvo);
    }
}
