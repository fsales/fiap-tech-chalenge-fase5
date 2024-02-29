package br.com.fsales.wells.app.infrastructure.database.postgres.adpter;

import br.com.fsales.wells.app.infrastructure.database.postgres.mapper.UsuarioEntityMapper;
import br.com.fsales.wells.app.infrastructure.database.postgres.repository.UsuarioEntityRepository;
import br.com.fsales.wells.core.domain.usuario.model.Usuario;
import br.com.fsales.wells.core.domain.usuario.repository.CadastrarUsuarioRepository;

public class CadastrarUsuarioAdapter implements CadastrarUsuarioRepository {

    private final UsuarioEntityRepository usuarioEntityRepository;

    public CadastrarUsuarioAdapter(UsuarioEntityRepository usuarioEntityRepository) {
        this.usuarioEntityRepository = usuarioEntityRepository;
    }

    @Override
    public Usuario execute(Usuario usuario) {

        var usuarioSalvar = UsuarioEntityMapper.convertToUsuarioEntity(usuario);

        var usuarioEntity = usuarioEntityRepository.save(usuarioSalvar);

        return UsuarioEntityMapper.convertToUsuario(usuarioEntity);
    }
}
