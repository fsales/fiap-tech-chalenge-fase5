package br.com.fsales.wells.core.domain.usuario.usecases.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.fsales.wells.core.domain.usuario.exception.UsernameUniqueViolationException;
import br.com.fsales.wells.core.domain.usuario.gateways.CadastrarUsuarioGateway;
import br.com.fsales.wells.core.domain.usuario.gateways.ConsultarUsuarioPorUsernameGateway;
import br.com.fsales.wells.core.domain.usuario.model.Role;
import br.com.fsales.wells.core.domain.usuario.model.Usuario;
import br.com.fsales.wells.core.domain.usuario.usecases.CadastrarUsuarioUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@DisplayName("Testes para CadastrarUsuarioUseCase")
class CadastrarUsuarioUseCaseIT {

    private AutoCloseable openMocks;
    @Mock
    private CadastrarUsuarioGateway cadastrarUsuarioGateway;

    @Mock
    private ConsultarUsuarioPorUsernameGateway consultarUsuarioPorUsernameGateway;

    private CadastrarUsuarioUseCase cadastrarUsuarioService;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);

        /** Instanciação manual dos mocks **/
        // Configuração dos mocks no serviço
        cadastrarUsuarioService = new CadastrarUsuarioUseCaseImpl(
                cadastrarUsuarioGateway,
                consultarUsuarioPorUsernameGateway
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
                    consultarUsuarioPorUsernameGateway.existsByUsername(anyString())
            ).thenReturn(false);
            when(
                    cadastrarUsuarioGateway.execute(any(Usuario.class))
            ).thenReturn(usuario);

            // Act
            var resultado = cadastrarUsuarioService.execute(usuario);

            // Assert
            assertThat(resultado).isEqualTo(usuario);
            assertThat(resultado.username()).isEqualTo(usuario.username());
            assertThat(resultado.senha()).isEqualTo(usuario.senha());
            assertThat(resultado.role()).isEqualTo(usuario.role());

            verify(consultarUsuarioPorUsernameGateway, times(1)).existsByUsername(anyString());
            verify(cadastrarUsuarioGateway, times(1)).execute(any(Usuario.class));
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
                    consultarUsuarioPorUsernameGateway.existsByUsername(anyString())
            ).thenReturn(false);
            when(
                    cadastrarUsuarioGateway.execute(any(Usuario.class))
            ).thenReturn(username);

            // Act
            var resultado = cadastrarUsuarioService.execute(username);

            assertThat(resultado).isEqualTo(username);
            assertThat(resultado.username()).isEqualTo(username.username());
            assertThat(resultado.senha()).isEqualTo(username.senha());
            assertThat(resultado.role()).isEqualTo(username.role());

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
            var usuario = Usuario.criar(
                    "cliente@wells.com",
                    "123456",
                    Role.ROLE_CLIENTE
            );
            // metoodo retona verdadeiro se existir usuario com o mesmo nome cadastrado
            when(consultarUsuarioPorUsernameGateway.existsByUsername(anyString())).thenReturn(true);

            // Act & Assert
            assertThrows(UsernameUniqueViolationException.class, () -> cadastrarUsuarioService.execute(usuario));

            // Verify
            verify(consultarUsuarioPorUsernameGateway).existsByUsername("cliente@wells.com");
            verify(cadastrarUsuarioGateway, never()).execute(any(Usuario.class));

            // AssertJ verification
            assertThatThrownBy(
                    () -> cadastrarUsuarioService.execute(usuario)
            )
                    .isInstanceOf(UsernameUniqueViolationException.class)
                    .hasMessageContaining("Usuário já cadastrado");
        }
    }
}
