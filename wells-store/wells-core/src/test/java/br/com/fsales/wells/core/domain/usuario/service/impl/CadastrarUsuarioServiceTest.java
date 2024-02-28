package br.com.fsales.wells.core.domain.usuario.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

import br.com.fsales.wells.core.domain.usuario.model.Role;
import br.com.fsales.wells.core.domain.usuario.model.Usuario;
import br.com.fsales.wells.core.domain.usuario.repository.CadastrarUsuarioRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CadastrarUsuarioServiceTest {

    private AutoCloseable openMocks;
    @Mock
    private CadastrarUsuarioRepository mockRepository;

    @InjectMocks
    private CadastrarUsuarioServiceImpl usuarioService;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Nested
    class RegistrarUsuario{
        @Test
        public void devePermitirRegistrarUsuario_RoleCliente() {
            // Arrange
            var usuario = Usuario.criar("cliente@wells.com", "123456", Role.ROLE_CLIENTE);
            when(mockRepository.execute(any(Usuario.class))).thenReturn(usuario);

            // Act
            var resultado = usuarioService.execute(usuario);

            assertThat(resultado).isEqualTo(usuario);
            assertThat(resultado.usuario()).isEqualTo(usuario.usuario());
            assertThat(resultado.senha()).isEqualTo(usuario.senha());
            assertThat(resultado.role()).isEqualTo(usuario.role());

            verify(mockRepository, times(1)).execute(any(Usuario.class));
        }

        @Test
        public void devePermitirRegistrarUsuario_RoleAdmin() {
            // Arrange
            var usuario = Usuario.criar("admin@wells.com", "123456", Role.ROLE_ADMIN);
            when(mockRepository.execute(any(Usuario.class))).thenReturn(usuario);

            // Act
            var resultado = usuarioService.execute(usuario);

            assertThat(resultado).isEqualTo(usuario);
            assertThat(resultado.usuario()).isEqualTo(usuario.usuario());
            assertThat(resultado.senha()).isEqualTo(usuario.senha());
            assertThat(resultado.role()).isEqualTo(usuario.role());

            verify(mockRepository, times(1)).execute(any(Usuario.class));
        }
    }

    @Nested
    class Validacao{

    }
}
