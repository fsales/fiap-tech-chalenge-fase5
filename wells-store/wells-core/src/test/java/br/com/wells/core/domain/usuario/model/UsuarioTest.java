package br.com.wells.core.domain.usuario.model;

import br.com.wells.core.domain.usuario.exception.SenhaInvalidaException;
import br.com.wells.core.domain.usuario.exception.UsuarioInvalidoException;
import org.junit.jupiter.api.*;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("Testes para UsuarioTest")
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
    @DisplayName("Método Registrar")
    class Registrar {

        @Test
        @DisplayName("Deve Criar Usuário como Admin")
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
        @DisplayName("Deve Criar Usuário como Cliente")
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
                    .isNotEqualTo(senha);
            ;
        }


        @Test
        @DisplayName("Deve Alterar Usuário com Dados Válidos")
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
    @DisplayName("Método Validar")
    class Validar {
        @Test
        @DisplayName("Deve lançar exceção ao criar usuário com e-mail inválido")
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
        @DisplayName("Deve lançar exceção ao criar usuário com senha inválida")
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