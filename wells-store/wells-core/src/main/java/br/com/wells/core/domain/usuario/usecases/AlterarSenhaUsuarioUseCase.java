package br.com.wells.core.domain.usuario.usecases;

public interface AlterarSenhaUsuarioUseCase {
    void execute(
            final Long id,
            final String senhaAtual,
            final String novaSenha,
            final String confirmaSenha
    );
}
