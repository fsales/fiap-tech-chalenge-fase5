package br.com.fsales.wells.core.domain.usuario.service.impl;

import br.com.fsales.wells.core.domain.usuario.exception.UsernameUniqueViolationException;
import br.com.fsales.wells.core.domain.usuario.model.Role;
import br.com.fsales.wells.core.domain.usuario.model.Usuario;
import br.com.fsales.wells.core.domain.usuario.repository.CadastrarUsuarioRepository;
import br.com.fsales.wells.core.domain.usuario.repository.ConsultarUsuarioPorUsernameRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@DisplayName("Testes para CadastrarUsuarioService")
class CadastrarUsuarioServiceIT {

    private AutoCloseable openMocks;
    @Mock
    private CadastrarUsuarioRepository cadastrarUsuarioRepository;

    @Mock
    private ConsultarUsuarioPorUsernameRepository consultarUsuarioPorUsernameRepository;

    private CadastrarUsuarioServiceImpl cadastrarUsuarioService;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);

        /** Instanciação manual dos mocks **/
        // Configuração dos mocks no serviço
        cadastrarUsuarioService = new CadastrarUsuarioServiceImpl(
                cadastrarUsuarioRepository,
                consultarUsuarioPorUsernameRepository
        );
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
            var usuario = Usuario.criar(
                    "cliente@wells.com",
                    "123456",
                    Role.ROLE_CLIENTE
            );

            when(
                    consultarUsuarioPorUsernameRepository.existsByUsername(anyString())
            ).thenReturn(false);
            when(
                    cadastrarUsuarioRepository.execute(any(Usuario.class))
            ).thenReturn(usuario);

            // Act
            var resultado = cadastrarUsuarioService.execute(usuario);

            // Assert
            assertThat(resultado).isEqualTo(usuario);
            assertThat(resultado.username()).isEqualTo(usuario.username());
            assertThat(resultado.senha()).isEqualTo(usuario.senha());
            assertThat(resultado.role()).isEqualTo(usuario.role());

            verify(consultarUsuarioPorUsernameRepository, times(1)).existsByUsername(anyString());
            verify(cadastrarUsuarioRepository, times(1)).execute(any(Usuario.class));
        }


        @Test
        @DisplayName("Deve permitir registrar usuário com role Admin")
        void devePermitir_RegistrarUsuario_RoleAdmin() {
            // Arrange
            var username = Usuario.criar(
                    "admin@wells.com",
                    "123456",
                    Role.ROLE_ADMIN
            );
            when(
                    consultarUsuarioPorUsernameRepository.existsByUsername(anyString())
            ).thenReturn(false);
            when(
                    cadastrarUsuarioRepository.execute(any(Usuario.class))
            ).thenReturn(username);

            // Act
            var resultado = cadastrarUsuarioService.execute(username);

            assertThat(resultado).isEqualTo(username);
            assertThat(resultado.username()).isEqualTo(username.username());
            assertThat(resultado.senha()).isEqualTo(username.senha());
            assertThat(resultado.role()).isEqualTo(username.role());

            verify(consultarUsuarioPorUsernameRepository, times(1)).existsByUsername(anyString());
            verify(cadastrarUsuarioRepository, times(1)).execute(any(Usuario.class));
        }

    }

    @Nested
    @DisplayName("Método Validar")
    class Validar{

        @Test
        @DisplayName("Deve lançar exceção ao cadastrar usuário com username existente")
        void deveLancarExcecao_CadastrarUsuario_ComUsernameExistente() {
            // Arrange
            var usuario = Usuario.criar(
                    "cliente@wells.com",
                    "123456",
                    Role.ROLE_CLIENTE
            );
            // metoodo retona verdadeiro se existir usuario com o mesmo nome cadastrado
            when(consultarUsuarioPorUsernameRepository.existsByUsername(anyString())).thenReturn(true);

            // Act & Assert
            assertThrows(UsernameUniqueViolationException.class, () -> cadastrarUsuarioService.execute(usuario));

            // Verify
            verify(consultarUsuarioPorUsernameRepository).existsByUsername("cliente@wells.com");
            verify(cadastrarUsuarioRepository, never()).execute(any(Usuario.class));

            // AssertJ verification
            assertThatThrownBy(
                    () -> cadastrarUsuarioService.execute(usuario)
            )
                    .isInstanceOf(UsernameUniqueViolationException.class)
                    .hasMessageContaining("Usuário já cadastrado");
        }
    }
}
