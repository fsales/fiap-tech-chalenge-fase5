package br.com.fsales.wells.app.usecase.usuario;

import br.com.fsales.wells.core.domain.usuario.model.Usuario;
import br.com.fsales.wells.core.domain.usuario.service.CadastrarUsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CadastrarUsuarioUseCaseImpl implements CadastrarUsuarioUseCase {

    private final CadastrarUsuarioService cadastrarUsuarioService;

    public CadastrarUsuarioUseCaseImpl(CadastrarUsuarioService cadastrarUsuarioService) {
        this.cadastrarUsuarioService = cadastrarUsuarioService;
    }

    @Override
    public Usuario execute(
            Usuario usuario
    ) {

        return cadastrarUsuarioService.execute(
                usuario
        );
    }
}
