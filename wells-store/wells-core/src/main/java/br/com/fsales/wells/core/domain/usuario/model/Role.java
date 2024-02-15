package br.com.fsales.wells.core.domain.usuario.model;

public enum Role {
    ADMIN("Administração do Sistema", Permissao.ADMIN),
    USER("Usuário Padrão", Permissao.USER);

    private final String descricao;
    private final Permissao permissao;

    Role(String descricao, Permissao permissao) {
        this.descricao = descricao;
        this.permissao = permissao;
    }

    public String getDescricao() {
        return descricao;
    }

    public Permissao getPermissao() {
        return permissao;
    }

    public enum Permissao {
        ADMIN, USER
    }
}
