package br.com.fsales.wells.core.domain.usuario.service.impl;

import br.com.fsales.wells.core.domain.usuario.model.Usuario;
import br.com.fsales.wells.core.domain.usuario.repository.CadastrarUsuarioRepository;
import br.com.fsales.wells.core.domain.usuario.service.CadastrarUsuarioService;

public class CadastrarUsuarioServiceImpl implements CadastrarUsuarioService {

    private final CadastrarUsuarioRepository cadastrarUsuarioRepository;

    public CadastrarUsuarioServiceImpl(CadastrarUsuarioRepository cadastrarUsuarioRepository) {
        this.cadastrarUsuarioRepository = cadastrarUsuarioRepository;
    }

    @Override
    public Usuario execute(Usuario usuario) {
        return cadastrarUsuarioRepository.execute(usuario);
    }
}
