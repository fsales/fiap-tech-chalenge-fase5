package br.com.wells.app.presentation.rest.controller.auth;

import br.com.wells.app.infrastructure.config.spring.config.app.ApiRoutes;
import br.com.wells.app.infrastructure.config.spring.security.jwt.JWTToken;
import br.com.wells.app.infrastructure.config.spring.security.user.UsuarioCustomDetails;
import br.com.wells.app.presentation.exception.ErrorMessage;
import br.com.wells.app.presentation.rest.controller.auth.dto.request.UsuarioLoginDto;
import br.com.wells.app.presentation.rest.controller.auth.dto.response.LoginResponseDTO;
import br.com.wells.app.presentation.rest.controller.auth.swagger.AutenticacaoControllerSwagger;
import br.com.wells.app.presentation.rest.controller.exception.WellsAuthenticationException;
import br.com.wells.app.presentation.rest.controller.response.generic.GenericResponse;
import br.com.wells.app.presentation.rest.validation.FindInfo;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiRoutes.AUTH_URI)

@RequiredArgsConstructor
@Slf4j
public class AuthController implements AutenticacaoControllerSwagger {

	public static final String FALHA_NA_AUTENTICACAO = "Falha na autenticação";

	private final AuthenticationManager authenticationManager;

	private final JWTToken jwtToken;

	@PostMapping("/login")
	@Override
	public ResponseEntity<GenericResponse<LoginResponseDTO>> autenticar(
			@Validated(FindInfo.class) @RequestBody UsuarioLoginDto usuarioLoginDto, HttpServletRequest request) {
		log.info("Processo de autenticação pelo login {}", usuarioLoginDto.username());

		try {
			var usernamePassword = new UsernamePasswordAuthenticationToken(usuarioLoginDto.username(),
					usuarioLoginDto.senha());

			var auth = authenticationManager.authenticate(usernamePassword);

			if (!auth.isAuthenticated()) {
				throw new WellsAuthenticationException(FALHA_NA_AUTENTICACAO);
			}

			var usuarioCustomDetails = (UsuarioCustomDetails) auth.getPrincipal();

			var token = jwtToken.generateToken(usuarioCustomDetails);

			return ResponseEntity.ok(GenericResponse.success(HttpStatus.OK, new LoginResponseDTO(token)));
		}
		catch (AuthenticationException | WellsAuthenticationException ex) {

			log.warn("Bad Credentials from username '{}'", usuarioLoginDto.username());
			ErrorMessage errorMessage = new ErrorMessage(request, HttpStatus.BAD_REQUEST, "Credenciais Inválidas");
			return ResponseEntity.badRequest().body(GenericResponse.error(HttpStatus.BAD_REQUEST, errorMessage));
		}

	}

	@PostMapping("/token")
	@Override
	public ResponseEntity<GenericResponse<LoginResponseDTO>> getToken(@RequestBody UsuarioLoginDto usuarioLoginDto,
			HttpServletRequest request) {
		try {
			Authentication authenticate = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(usuarioLoginDto.username(), usuarioLoginDto.senha()));
			if (!authenticate.isAuthenticated()) {
				throw new WellsAuthenticationException(FALHA_NA_AUTENTICACAO);
			}

			var token = jwtToken.generateToken((UsuarioCustomDetails) authenticate.getPrincipal());
			return ResponseEntity.ok(GenericResponse.success(HttpStatus.OK, new LoginResponseDTO(token)));
		}
		catch (AuthenticationException | WellsAuthenticationException e) {
			return ResponseEntity.badRequest()
				.body(GenericResponse.error(HttpStatus.UNAUTHORIZED,
						new ErrorMessage(request, HttpStatus.UNAUTHORIZED, FALHA_NA_AUTENTICACAO)));
		}
	}

	@GetMapping("/validate")
	@Override
	public ResponseEntity<GenericResponse<String>> validateToken(@RequestParam("token") String token) {
		try {
			jwtToken.validateToken(token);
		}
		catch (JWTVerificationException e) {
			throw new WellsAuthenticationException("Token inválido");
		}

		return ResponseEntity.ok(GenericResponse.success(HttpStatus.OK, "Token é válido."));
	}

}
