package br.com.wells.core.domain.usuario.usecases.impl;

import br.com.wells.core.domain.usuario.exception.EntityNotFoundException;
import br.com.wells.core.domain.usuario.gateways.ConsultarUsuarioPorUsernameGateway;
import br.com.wells.core.domain.usuario.model.Usuario;
import br.com.wells.core.domain.usuario.usecases.ConsultarUsuarioPorUsernameUseCase;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@DisplayName("Testes para ConsultarUsuarioPorUsernameUseCase")
class ConsultarUsuarioPorUsernameUseCaseIT {

	public static final String CLIENTE = "CLIENTE";

	private AutoCloseable openMocks;

	@Mock
	private ConsultarUsuarioPorUsernameGateway consultarUsuarioPorUsernameGateway;

	private ConsultarUsuarioPorUsernameUseCase consultarUsuarioPorUsernameUseCase;

	@BeforeEach
	void setUp() {
		openMocks = MockitoAnnotations.openMocks(this);

		consultarUsuarioPorUsernameUseCase = new ConsultarUsuarioPorUsernameUseCaseImpl(
				consultarUsuarioPorUsernameGateway);
	}

	@AfterEach
	void tearDown() throws Exception {
		openMocks.close();
	}

	@Nested
	@DisplayName("ConsultarUsuario")
	class ConsultarUsuario {

		@Test
		@DisplayName("Deve permitir consultar usuário pelo username")
		void devePermitir_ConsultarUsuarioPorUsername() {
			// Arrange
			var username = "cliente@wells.com";
			Usuario usuarioMock = Usuario.criar("cliente@wells.com", "123456", Set.of(CLIENTE));
			when(consultarUsuarioPorUsernameGateway.find(username)).thenReturn(Optional.of(usuarioMock));

			// Act
			Usuario resultado = consultarUsuarioPorUsernameUseCase.find(username);

			// Assert
			assertThat(resultado).isNotNull().extracting(Usuario::username).isEqualTo(usuarioMock.username());
		}

		@Test
		@DisplayName("Deve lançar exceção ao consultar um username que não existe")
		void deveLancarExcecao_QuandoUsuarioNaoEncontrado() {
			// Arrange
			var username = "cliente@wells.com";
			when(consultarUsuarioPorUsernameGateway.find(username)).thenReturn(Optional.empty());

			// Act & Assert
			assertThrows(EntityNotFoundException.class, () -> consultarUsuarioPorUsernameUseCase.find(username));

			assertThatThrownBy(() -> consultarUsuarioPorUsernameUseCase.find(username))
				.isInstanceOf(EntityNotFoundException.class)
				.hasMessageContaining(String.format("Usuario com '%s' não encontrado", username));
		}

	}

}