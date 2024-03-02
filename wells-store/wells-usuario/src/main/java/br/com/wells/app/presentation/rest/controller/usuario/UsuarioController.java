package br.com.wells.app.presentation.rest.controller.usuario;


import br.com.wells.app.presentation.rest.controller.usuario.dto.mapper.UsuarioDtoMapper;
import br.com.wells.app.presentation.rest.controller.usuario.dto.request.UsuarioAtualizarSenhaDto;
import br.com.wells.app.presentation.rest.controller.usuario.dto.request.UsuarioCadastrarDto;
import br.com.wells.app.presentation.rest.controller.usuario.dto.response.UsuarioResponseDto;
import br.com.wells.app.presentation.rest.controller.usuario.swagger.UsuarioControllerSwagger;
import br.com.wells.app.presentation.rest.validation.CreateInfo;
import br.com.wells.core.domain.usuario.usecases.AlterarSenhaUsuarioUseCase;
import br.com.wells.core.domain.usuario.usecases.CadastrarUsuarioUseCase;
import br.com.wells.core.domain.usuario.usecases.ConsutlarTodosUsuarioUseCase;
import br.com.wells.core.domain.usuario.usecases.ConsutlarUsuarioPorIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuarios")

@RequiredArgsConstructor
public class UsuarioController implements UsuarioControllerSwagger {

    private final CadastrarUsuarioUseCase cadastrarUsuarioUseCase;

    private final ConsutlarUsuarioPorIdUseCase consutlarUsuarioPorIdUseCase;

    private final AlterarSenhaUsuarioUseCase alterarSenhaUsuarioUseCase;

    private final ConsutlarTodosUsuarioUseCase consutlarTodosUsuarioUseCase;

    @PostMapping
    @Override
    public ResponseEntity<UsuarioResponseDto> cadastrar(
            @Validated(CreateInfo.class)
            @RequestBody
            UsuarioCadastrarDto cadastrarDto
    ) {
        var usuario = UsuarioDtoMapper.convertToUsuario(cadastrarDto);
        var usuarioSalvo = cadastrarUsuarioUseCase.execute(usuario);
        var usuarioResponseDto = UsuarioDtoMapper.convertToUsuarioResponseDto(usuarioSalvo);

        return ResponseEntity.ok(usuarioResponseDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') OR ( hasRole('CLIENTE') AND #id == authentication.principal.id)")
    @Override
    public ResponseEntity<UsuarioResponseDto> consultarUsarioPorId(
            @PathVariable
            Long id
    ) {
        var usuario = consutlarUsuarioPorIdUseCase.find(id);

        return ResponseEntity.ok(
                UsuarioDtoMapper.convertToUsuarioResponseDto(usuario)
        );
    }


    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENTE') AND (#id == authentication.principal.id)")
    @Override
    public ResponseEntity<Void> alterarSenha(
            @PathVariable Long id,
            @Validated(CreateInfo.class) @RequestBody
            UsuarioAtualizarSenhaDto atualizarSenhaDto
    ) {
        alterarSenhaUsuarioUseCase.execute(
                id,
                atualizarSenhaDto.senhaAtual(),
                atualizarSenhaDto.novaSenha(),
                atualizarSenhaDto.confirmaSenha()
        );
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public ResponseEntity<Page<UsuarioResponseDto>> listarTodos(
            @PageableDefault(page = 0, size = 10)
            Pageable pageable
    ) {
        var paginaUsuario = consutlarTodosUsuarioUseCase.find(
                pageable.getPageNumber(),
                pageable.getPageSize()
        );

        var usuarioResponseDtoPage = new PageImpl<>(
                paginaUsuario.list(),
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()),
                paginaUsuario.totalElements()
        ).map(
                UsuarioDtoMapper::convertToUsuarioResponseDto
        );

        return ResponseEntity.ok(
                usuarioResponseDtoPage
        );
    }
}
