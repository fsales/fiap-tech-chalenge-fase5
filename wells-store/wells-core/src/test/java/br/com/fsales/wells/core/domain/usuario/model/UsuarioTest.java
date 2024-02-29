package br.com.fsales.wells.core.domain.usuario.model;

import br.com.fsales.wells.core.domain.usuario.exception.SenhaInvalidaException;
import br.com.fsales.wells.core.domain.usuario.exception.UsuarioInvalidoException;import br.com.fsales.wells.core.domain.usuario.model.Role;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class UsuarioTest {

    private AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Nested
    class Registrar {

        @Test
        void testCriarUsuario_ComoAdmin() {
            String username = "admin@example.com";
            String senha = "adminPassword123";
            Role role = Role.ROLE_ADMIN;

            Usuario result = Usuario.criar(username, senha, role);

            assertThat(result)
                    .isNotNull()
                    .extracting(Usuario::username, Usuario::role)
                    .containsExactly(username, role);
        }

        @Test
        void testCriarUsuario_ComoCliente() {
            String username = "test@example.com";
            String senha = "password123";
            Role role = Role.ROLE_CLIENTE;

            Usuario result = Usuario.criar(username, senha, role);

            assertThat(result)
                    .isNotNull()
                    .extracting(Usuario::username, Usuario::role)
                    .containsExactly(username, role);

            // Verifica se a senha não é nula e nem vazia
            assertThat(result.senha())
                    .isNotBlank()
                    .isNotEqualTo(senha);;
        }


        @Test
        void testAlterarUsuario_ComDadosValidos() {
            Long id = 1L;
            String username = "test@example.com";
            String senha = "newPassword123";
            String senhaOld = "oldPassword";
            Role role = Role.ROLE_CLIENTE;

            Usuario originalUser = Usuario.criar(username, senhaOld, role);

            Usuario result = originalUser.alterar(id, senha, role);

            assertThat(result)
                    .isNotNull()
                    .satisfies(u -> {
                        assertThat(u.id()).isNotNull();
                        assertThat(u.senha())
                                .isNotBlank()
                                .isNotEqualTo(senhaOld);
                        assertThat(u.role()).isEqualTo(role);
                        assertThat(u.dataCriacao()).isNotNull();
                    });
        }
    }

    @Nested
    class Validacao {
        @Test
        void criarUsuario_EmailInvalido() {

            /** act e assert **/
            String username = "invalidEmail";
            String senha = "senha123";
            Role role = Role.ROLE_CLIENTE;

            assertThatThrownBy(() -> Usuario.criar(username, senha, role))
                    .isInstanceOf(UsuarioInvalidoException.class)
                    .hasMessageContaining("O campo 'username' não é um e-mail válido");
        }

        @Test
        void criarUsuario_SenhaInvalido() {

            /** act e assert **/
            String username = "admin@wells.com";
            String senha = "senh";
            Role role = Role.ROLE_CLIENTE;

            assertThatThrownBy(() -> Usuario.criar(username, senha, role))
                    .isInstanceOf(SenhaInvalidaException.class)
                    .hasMessageContaining("A senha deve ter no mínimo 6 caracteres");
        }

    }
}