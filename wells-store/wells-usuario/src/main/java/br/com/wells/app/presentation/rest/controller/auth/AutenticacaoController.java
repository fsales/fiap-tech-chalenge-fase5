package br.com.wells.app.presentation.rest.controller.auth;

import br.com.wells.app.infrastructure.spring.security.user.UsuarioCustomDetails;
import br.com.wells.app.presentation.exception.ErrorMessage;
import br.com.wells.app.presentation.rest.controller.auth.dto.request.UsuarioLoginDto;
import br.com.wells.app.presentation.rest.controller.auth.dto.response.LoginResponseDTO;
import br.com.wells.app.presentation.rest.controller.auth.swagger.AutenticacaoControllerSwagger;
import br.com.wells.app.presentation.rest.validation.FindInfo;
import br.com.wells.app.infrastructure.spring.security.jwt.JWTToken;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")

@RequiredArgsConstructor
@Slf4j
public class AutenticacaoController implements AutenticacaoControllerSwagger {

    private final AuthenticationManager authenticationManager;

    private final JWTToken JWTToken;

    @PostMapping("/login")
    @Override
    public ResponseEntity<?> autenticar(
            @Validated(FindInfo.class)
            @RequestBody
            UsuarioLoginDto usuarioLoginDto,
            HttpServletRequest request
    ) {
        log.info("Processo de autenticação pelo login {}", usuarioLoginDto.username());
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(
                    usuarioLoginDto.username(),
                    usuarioLoginDto.senha()
            );

            var auth = authenticationManager.authenticate(
                    usernamePassword
            );

            var usuarioCustomDetails = (UsuarioCustomDetails) auth.getPrincipal();

            var token = JWTToken.generateToken(usuarioCustomDetails);
            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (AuthenticationException ex) {
            log.warn("Bad Credentials from username '{}'", usuarioLoginDto.username());
        }

        return ResponseEntity
                .badRequest()
                .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, "Credenciais Inválidas"));
    }
}
