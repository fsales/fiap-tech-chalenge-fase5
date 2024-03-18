package br.com.wells.core.domain.usuario.usecases.impl;

import br.com.wells.core.domain.usuario.exception.UsernameUniqueViolationException;
import br.com.wells.core.domain.usuario.gateways.CadastrarUsuarioGateway;
import br.com.wells.core.domain.usuario.gateways.ConsultarRolePorNomeGateway;
import br.com.wells.core.domain.usuario.gateways.ConsultarUsuarioPorUsernameGateway;
import br.com.wells.core.domain.usuario.model.Role;
import br.com.wells.core.domain.usuario.model.Usuario;
import br.com.wells.core.domain.usuario.usecases.CadastrarUsuarioUseCase;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@DisplayName("Testes para CadastrarUsuarioUseCase")
class CadastrarUsuarioUseCaseIT {

	public static final String CLIENTE = "CLIENTE";

	public static final String ADMIN = "ADMIN";

	private AutoCloseable openMocks;

	@Mock
	private CadastrarUsuarioGateway cadastrarUsuarioGateway;

	@Mock
	private ConsultarUsuarioPorUsernameGateway consultarUsuarioPorUsernameGateway;

	@Mock
	ConsultarRolePorNomeGateway consultarRolePorNomeGateway;

	private CadastrarUsuarioUseCase cadastrarUsuarioUseCase;

	@BeforeEach
	void setup() {
		openMocks = MockitoAnnotations.openMocks(this);

		/** Instanciação manual dos mocks **/
		// Configuração dos mocks no serviço
		cadastrarUsuarioUseCase = new CadastrarUsuarioUseCaseImpl(cadastrarUsuarioGateway,
				consultarUsuarioPorUsernameGateway, consultarRolePorNomeGateway);
	}

	@AfterEach
	void tearDown() throws Exception {
		openMocks.close();
	}

	@Nested
	@DisplayName("RegistrarUsuario")
	class RegistrarUsuario {

		@Test
		@DisplayName("Deve permitir registrar usuário com role Cliente")
		void devePermitir_RegistrarUsuario_RoleCliente() {
			// Arrange
			var roles = Set.of(CLIENTE);
			var rolesObj = Set.of(Role.criar(CLIENTE));
			var usuario = Usuario.criar("cliente@wells.com", "123456", roles);

			when(consultarUsuarioPorUsernameGateway.existsByUsername(anyString())).thenReturn(false);

			when(consultarRolePorNomeGateway.find(roles)).thenReturn(Optional.of(rolesObj));

			when(cadastrarUsuarioGateway.execute(any(Usuario.class))).thenReturn(usuario);

			// Act
			var resultado = cadastrarUsuarioUseCase.execute(usuario);

			// Assert
			assertThat(resultado).isEqualTo(usuario);
			assertThat(resultado.username()).isEqualTo(usuario.username());
			assertThat(resultado.senha()).isEqualTo(usuario.senha());
			assertThat(resultado.roles().stream().map(Role::nome).collect(Collectors.toSet())).isEqualTo(roles);

			verify(consultarUsuarioPorUsernameGateway, times(1)).existsByUsername(anyString());
			verify(cadastrarUsuarioGateway, times(1)).execute(any(Usuario.class));
		}

		@Test
		@DisplayName("Deve permitir registrar usuário com role Admin")
		void devePermitir_RegistrarUsuario_RoleAdmin() {
			// Arrange
			var roles = Set.of(ADMIN);
			var rolesObj = Set.of(Role.criar(ADMIN));
			var username = Usuario.criar("admin@wells.com", "123456", roles);
			when(consultarUsuarioPorUsernameGateway.existsByUsername(anyString())).thenReturn(false);

			when(consultarRolePorNomeGateway.find(roles)).thenReturn(Optional.of(rolesObj));

			when(cadastrarUsuarioGateway.execute(any(Usuario.class))).thenReturn(username);

			// Act
			var resultado = cadastrarUsuarioUseCase.execute(username);

			// Assert
			assertThat(resultado).isEqualTo(username);
			assertThat(resultado.username()).isEqualTo(username.username());
			assertThat(resultado.senha()).isEqualTo(username.senha());

			// Desembrulhe o Optional e compare os conjuntos diretamente
			assertThat(resultado.roles().stream().map(Role::nome).collect(Collectors.toSet())).isEqualTo(roles);

			verify(consultarUsuarioPorUsernameGateway, times(1)).existsByUsername(anyString());
			verify(cadastrarUsuarioGateway, times(1)).execute(any(Usuario.class));
		}

	}

	@Nested
	@DisplayName("Método Validar")
	class Validar {

		@Test
		@DisplayName("Deve lançar exceção ao cadastrar usuário com username existente")
		void deveLancarExcecao_CadastrarUsuario_ComUsernameExistente() {
			// Arrange
			var usuario = Usuario.criar("cliente@wells.com", "123456", Set.of(CLIENTE));
			// metoodo retona verdadeiro se existir usuario com o mesmo nome cadastrado
			when(consultarUsuarioPorUsernameGateway.existsByUsername(anyString())).thenReturn(true);

			// Act & Assert
			assertThrows(UsernameUniqueViolationException.class, () -> cadastrarUsuarioUseCase.execute(usuario));

			// Verify
			verify(consultarUsuarioPorUsernameGateway).existsByUsername("cliente@wells.com");
			verify(cadastrarUsuarioGateway, never()).execute(any(Usuario.class));

			// AssertJ verification
			assertThatThrownBy(() -> cadastrarUsuarioUseCase.execute(usuario))
				.isInstanceOf(UsernameUniqueViolationException.class)
				.hasMessageContaining("Usuário já cadastrado");
		}

	}

}
