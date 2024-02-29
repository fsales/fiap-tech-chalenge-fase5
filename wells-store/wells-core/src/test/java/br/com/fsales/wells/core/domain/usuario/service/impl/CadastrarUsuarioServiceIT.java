package br.com.fsales.wells.core.domain.usuario.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.fsales.wells.core.domain.usuario.model.Role;
import br.com.fsales.wells.core.domain.usuario.model.Usuario;
import br.com.fsales.wells.core.domain.usuario.repository.CadastrarUsuarioRepository;
import br.com.fsales.wells.core.domain.usuario.repository.ConsultarUsuarioPorUsernameRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class CadastrarUsuarioServiceIT {

    private AutoCloseable openMocks;
    @Mock
    private CadastrarUsuarioRepository mockRepository;

    @Mock
    private ConsultarUsuarioPorUsernameRepository mockConsultarUsuarioRepository;

    private CadastrarUsuarioServiceImpl usernameService;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);

        /** Instanciação manual dos mocks **/
        // Configuração dos mocks no serviço
        usernameService = new CadastrarUsuarioServiceImpl(mockRepository, mockConsultarUsuarioRepository);
    }


    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Nested
    class RegistrarUsuario {
        @Test
        void devePermitirRegistrarUsuario_RoleCliente() {
            // Arrange
            var usuario = Usuario.criar("cliente@wells.com", "123456", Role.ROLE_CLIENTE);
            when(mockConsultarUsuarioRepository.existsByUsername(anyString())).thenReturn(false);
            when(mockRepository.execute(any(Usuario.class))).thenReturn(usuario);

            // Act
            var resultado = usernameService.execute(usuario);

            // Assert
            assertThat(resultado).isEqualTo(usuario);
            assertThat(resultado.username()).isEqualTo(usuario.username());
            assertThat(resultado.senha()).isEqualTo(usuario.senha());
            assertThat(resultado.role()).isEqualTo(usuario.role());

            verify(mockConsultarUsuarioRepository, times(1)).existsByUsername(anyString());
            verify(mockRepository, times(1)).execute(any(Usuario.class));
        }


        @Test
        void devePermitirRegistrarUsuario_RoleAdmin() {
            // Arrange
            var username = Usuario.criar("admin@wells.com", "123456", Role.ROLE_ADMIN);
            when(mockConsultarUsuarioRepository.existsByUsername(anyString())).thenReturn(false);
            when(mockRepository.execute(any(Usuario.class))).thenReturn(username);

            // Act
            var resultado = usernameService.execute(username);

            assertThat(resultado).isEqualTo(username);
            assertThat(resultado.username()).isEqualTo(username.username());
            assertThat(resultado.senha()).isEqualTo(username.senha());
            assertThat(resultado.role()).isEqualTo(username.role());

            verify(mockConsultarUsuarioRepository, times(1)).existsByUsername(anyString());
            verify(mockRepository, times(1)).execute(any(Usuario.class));
        }

    }
}
