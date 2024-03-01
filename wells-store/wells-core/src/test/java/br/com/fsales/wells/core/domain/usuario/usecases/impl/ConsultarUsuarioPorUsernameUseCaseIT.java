package br.com.fsales.wells.core.domain.usuario.usecases.impl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import br.com.fsales.wells.core.domain.usuario.exception.EntityNotFoundException;
import br.com.fsales.wells.core.domain.usuario.gateways.ConsultarUsuarioPorUsernameGateway;
import br.com.fsales.wells.core.domain.usuario.model.Role;
import br.com.fsales.wells.core.domain.usuario.model.Usuario;
import br.com.fsales.wells.core.domain.usuario.usecases.ConsultarUsuarioPorUsernameUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@DisplayName("Testes para ConsultarUsuarioPorUsernameUseCase")
class ConsultarUsuarioPorUsernameUseCaseIT {

    private AutoCloseable openMocks;

    @Mock
    private ConsultarUsuarioPorUsernameGateway consultarUsuarioPorUsernameGateway;

    private ConsultarUsuarioPorUsernameUseCase consultarUsuarioPorUsernameUseCase;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);

        consultarUsuarioPorUsernameUseCase = new ConsultarUsuarioPorUsernameUseCaseImpl(
                consultarUsuarioPorUsernameGateway
        );
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
            Usuario usuarioMock = Usuario.criar(
                    "cliente@wells.com",
                    "123456",
                    Role.ROLE_CLIENTE
            );
            when(consultarUsuarioPorUsernameGateway.find(username)).thenReturn(Optional.of(usuarioMock));

            // Act
            Usuario resultado = consultarUsuarioPorUsernameUseCase.find(username);

            // Assert
            assertThat(resultado)
                    .isNotNull()
                    .extracting(Usuario::username)
                    .isEqualTo(usuarioMock.username());
        }

        @Test
        @DisplayName("Deve lançar exceção ao consultar um username que não existe")
        void deveLancarExcecao_QuandoUsuarioNaoEncontrado() {
            // Arrange
            var username = "cliente@wells.com";
            when(consultarUsuarioPorUsernameGateway.find(username)).thenReturn(Optional.empty());

            // Act & Assert
            assertThrows(
                    EntityNotFoundException.class,
                    () -> consultarUsuarioPorUsernameUseCase.find(username)
            );

            assertThatThrownBy(
                    () -> consultarUsuarioPorUsernameUseCase.find(username))
                    .isInstanceOf(EntityNotFoundException.class)
                    .hasMessageContaining(String.format("Usuario com '%s' não encontrado", username));
        }
    }

}