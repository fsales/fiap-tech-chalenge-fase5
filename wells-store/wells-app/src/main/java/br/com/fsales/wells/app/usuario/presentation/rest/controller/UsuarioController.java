package br.com.fsales.wells.app.usuario.presentation.rest.controller;


import br.com.fsales.wells.app.usuario.presentation.rest.controller.dto.UsuarioCadastrarDto;
import br.com.fsales.wells.app.usuario.presentation.rest.controller.dto.UsuarioResponseDto;
import br.com.fsales.wells.app.usuario.presentation.rest.controller.swagger.UsuarioControllerSwagger;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")

@RequiredArgsConstructor
public class UsuarioController implements UsuarioControllerSwagger {


    @Override
    public ResponseEntity<UsuarioResponseDto> cadastrar(
            UsuarioCadastrarDto cadastrarDto
    ){
        return null;
    }
}
