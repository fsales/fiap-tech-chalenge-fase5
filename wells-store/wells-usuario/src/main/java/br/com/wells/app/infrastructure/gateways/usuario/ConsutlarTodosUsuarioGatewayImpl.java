package br.com.wells.app.infrastructure.gateways.usuario;

import br.com.wells.app.infrastructure.database.postgres.repository.UsuarioEntityRepository;
import br.com.wells.app.infrastructure.gateways.usuario.mapper.UsuarioEntityMapper;
import br.com.wells.core.domain.page.Pagina;
import br.com.wells.core.domain.usuario.gateways.ConsutlarTodosUsuarioGateway;
import br.com.wells.core.domain.usuario.model.Usuario;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class ConsutlarTodosUsuarioGatewayImpl implements ConsutlarTodosUsuarioGateway {

    private final UsuarioEntityRepository usuarioEntityRepository;

    public ConsutlarTodosUsuarioGatewayImpl(
            UsuarioEntityRepository usuarioEntityRepository
    ) {
        this.usuarioEntityRepository = usuarioEntityRepository;
    }

    @Override
    public Pagina<Usuario> find(
            int pageNumber,
            int pageSize
    ) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );

        var usuarioPage = usuarioEntityRepository.findAll(
                pageable
        );

        return new Pagina<>(
                usuarioPage.getContent(),
                usuarioPage.getTotalElements()
        ).map(
                UsuarioEntityMapper::convertToUsuario
        );
    }
}
