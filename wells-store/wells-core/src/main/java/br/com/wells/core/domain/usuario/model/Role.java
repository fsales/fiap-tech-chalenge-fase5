package br.com.wells.core.domain.usuario.model;

public enum Role {
    ROLE_ADMIN(Permissao.ADMIN),
    ROLE_CLIENTE(Permissao.CLIENTE);

    private final Permissao permissao;

    Role(Permissao permissao) {
        this.permissao = permissao;
    }

    public Permissao getPermissao() {
        return permissao;
    }

    public enum Permissao {

        ADMIN("Administração do Sistema"),
        CLIENTE("Usuário cliente");
        private final String descricao;

        Permissao(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }

    }
}
