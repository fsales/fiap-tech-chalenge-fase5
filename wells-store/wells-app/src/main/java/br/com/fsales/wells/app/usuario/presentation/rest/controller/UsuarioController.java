package br.com.fsales.wells.app.usuario.presentation.rest.controller;


import br.com.fsales.wells.app.usuario.presentation.rest.controller.dto.UsuarioCadastrarDto;
import br.com.fsales.wells.app.usuario.presentation.rest.controller.dto.UsuarioResponseDto;
import br.com.fsales.wells.app.usuario.presentation.rest.controller.swagger.UsuarioControllerSwagger;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/usuarios")

@RequiredArgsConstructor
public class UsuarioController implements UsuarioControllerSwagger {


    @PostMapping
    @Override
    public ResponseEntity<UsuarioResponseDto> cadastrar(
            @Valid
            @RequestBody
            UsuarioCadastrarDto cadastrarDto
    ){
        return null;
    }
}
