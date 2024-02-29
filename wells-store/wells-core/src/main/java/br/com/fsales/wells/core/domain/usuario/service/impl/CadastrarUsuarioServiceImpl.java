package br.com.fsales.wells.core.domain.usuario.service.impl;

import br.com.fsales.wells.core.domain.usuario.exception.UsernameUniqueViolationException;
import br.com.fsales.wells.core.domain.usuario.model.Usuario;
import br.com.fsales.wells.core.domain.usuario.repository.CadastrarUsuarioRepository;
import br.com.fsales.wells.core.domain.usuario.repository.ConsultarUsuarioPorUsernameRepository;
import br.com.fsales.wells.core.domain.usuario.service.CadastrarUsuarioService;

public class CadastrarUsuarioServiceImpl implements CadastrarUsuarioService {

    private final CadastrarUsuarioRepository cadastrarUsuarioRepository;
    private final ConsultarUsuarioPorUsernameRepository consultarUsuarioPorUsername;

    public CadastrarUsuarioServiceImpl(
            CadastrarUsuarioRepository cadastrarUsuarioRepository,
            ConsultarUsuarioPorUsernameRepository consultarUsuarioPorUsername
    ) {
        this.cadastrarUsuarioRepository = cadastrarUsuarioRepository;
        this.consultarUsuarioPorUsername = consultarUsuarioPorUsername;
    }

    @Override
    public Usuario execute(Usuario usuario) {
        validarUserName(usuario);

        return cadastrarUsuarioRepository.execute(usuario);
    }

    private void validarUserName(Usuario usuario) {
        var username = usuario.username();
        if (username.trim().isEmpty())
            return;

        if (consultarUsuarioPorUsername.existsByUsername(username)) {
            throw new UsernameUniqueViolationException("Usuário já cadastrado");
        }
    }
}
