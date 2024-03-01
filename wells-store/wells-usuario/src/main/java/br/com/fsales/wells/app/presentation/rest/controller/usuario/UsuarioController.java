package br.com.fsales.wells.app.presentation.rest.controller.usuario;


import br.com.fsales.wells.app.presentation.rest.controller.usuario.dto.mapper.UsuarioDtoMapper;
import br.com.fsales.wells.app.presentation.rest.controller.usuario.dto.request.UsuarioCadastrarDto;
import br.com.fsales.wells.app.presentation.rest.controller.usuario.dto.response.UsuarioResponseDto;
import br.com.fsales.wells.app.presentation.rest.controller.usuario.swagger.UsuarioControllerSwagger;
import br.com.fsales.wells.core.domain.usuario.usecases.CadastrarUsuarioUseCase;
import br.com.fsales.wells.core.domain.usuario.usecases.ConsutlarUsuarioPorIdUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/usuarios")

@RequiredArgsConstructor
public class UsuarioController implements UsuarioControllerSwagger {

    private final CadastrarUsuarioUseCase cadastrarUsuarioUseCase;

    private final ConsutlarUsuarioPorIdUseCase consutlarUsuarioPorIdUseCase;

    @PostMapping
    @Override
    public ResponseEntity<UsuarioResponseDto> cadastrar(
            @Valid
            @RequestBody
            UsuarioCadastrarDto cadastrarDto
    ) {
        var usuario = UsuarioDtoMapper.convertToUsuario(cadastrarDto);
        var usuarioSalvo = cadastrarUsuarioUseCase.execute(usuario);
        var usuarioResponseDto = UsuarioDtoMapper.convertToUsuarioResponseDto(usuarioSalvo);

        return ResponseEntity.ok(usuarioResponseDto);
    }

    @GetMapping("/{id}")
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
}
