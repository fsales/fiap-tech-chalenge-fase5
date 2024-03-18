package br.com.wells.core.domain.usuario.usecases.impl;

import br.com.wells.core.domain.usuario.exception.EntityNotFoundException;
import br.com.wells.core.domain.usuario.gateways.ConsutlarUsuarioPorIdGateway;
import br.com.wells.core.domain.usuario.model.Role;
import br.com.wells.core.domain.usuario.model.Usuario;
import br.com.wells.core.domain.usuario.usecases.ConsutlarUsuarioPorIdUseCase;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@DisplayName("Testes para ConsutlarUsuarioPorIdUseCaseIT")
class ConsutlarUsuarioPorIdUseCaseIT {

	public static final String CLIENTE = "CLIENTE";

	private AutoCloseable openMocks;

	@Mock
	private ConsutlarUsuarioPorIdGateway consutlarUsuarioPorIdGateway;

	private ConsutlarUsuarioPorIdUseCase consutlarUsuarioPorIdUseCase;

	@BeforeEach
	void setUp() {
		openMocks = MockitoAnnotations.openMocks(this);

		consutlarUsuarioPorIdUseCase = new ConsutlarUsuarioPorIdUseCaseImpl(consutlarUsuarioPorIdGateway);
	}

	@AfterEach
	void tearDown() throws Exception {
		openMocks.close();
	}

	@Nested
	@DisplayName("ConsultarUsuario")
	class ConsultarUsuario {

		@Test
		@DisplayName("Deve permitir consultar o usuário pelo ID")
		void devePermitir_ConsultarUsuarioPorId() {
			// Arrange
			var idUsuario = 1L;
			Usuario usuarioMock = new Usuario(idUsuario, "cliente@wells.com", "123456", Set.of(Role.criar(CLIENTE)),
					LocalDateTime.now(), LocalDateTime.now());

			when(consutlarUsuarioPorIdGateway.find(idUsuario)).thenReturn(Optional.of(usuarioMock));

			// act
			var resultado = consutlarUsuarioPorIdUseCase.find(idUsuario);

			// assert
			assertThat(resultado).isNotNull().isEqualTo(usuarioMock).extracting(Usuario::id).isEqualTo(idUsuario);
		}

		@Test
		@DisplayName("Deve lançar exceção ao consultar um username que não existe")
		void deveLancarExcecao_QuandoIdNaoEncontrado() {
			// Arrange
			var idUsuario = 1L;
			when(consutlarUsuarioPorIdGateway.find(idUsuario)).thenReturn(Optional.empty());

			// Act & Assert
			assertThrows(EntityNotFoundException.class, () -> consutlarUsuarioPorIdUseCase.find(idUsuario));

			assertThatThrownBy(() -> consutlarUsuarioPorIdUseCase.find(idUsuario))
				.isInstanceOf(EntityNotFoundException.class)
				.hasMessageContaining(String.format("Usuário id=%s não encontrado", idUsuario));
		}

	}

}