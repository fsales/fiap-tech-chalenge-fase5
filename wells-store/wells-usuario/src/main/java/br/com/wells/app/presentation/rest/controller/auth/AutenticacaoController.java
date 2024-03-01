package br.com.wells.app.presentation.rest.controller.auth;

import br.com.wells.app.presentation.rest.controller.auth.dto.request.UsuarioLoginDto;
import br.com.wells.app.presentation.rest.controller.auth.swagger.AutenticacaoControllerSwagger;
import br.com.wells.app.presentation.rest.validation.FindInfo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")

@RequiredArgsConstructor
public class AutenticacaoController implements AutenticacaoControllerSwagger {

    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    @Override
    public ResponseEntity<?> autenticar(
            @Validated(FindInfo.class)
            @RequestBody
            UsuarioLoginDto usuarioLoginDto,
            HttpServletRequest request
    ){

       var usernamePassword = new UsernamePasswordAuthenticationToken(
               usuarioLoginDto.username(),
               usuarioLoginDto.senha()
       );

        var auth = authenticationManager.authenticate(
                usernamePassword
        );

        return ResponseEntity.ok().build();
    }
}
