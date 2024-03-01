package br.com.fsales.wells.core.domain.usuario.usecases.impl;

import br.com.fsales.wells.core.domain.usuario.exception.SenhaInvalidaException;
import br.com.fsales.wells.core.domain.usuario.gateways.AlterarSenhaUsuarioGateway;
import br.com.fsales.wells.core.domain.usuario.model.SenhaCriptografada;
import br.com.fsales.wells.core.domain.usuario.usecases.AlterarSenhaUsuarioUseCase;
import br.com.fsales.wells.core.domain.usuario.usecases.ConsutlarUsuarioPorIdUseCase;

import java.util.Objects;

public final class AlterarSenhaUsuarioUseCaseImpl implements AlterarSenhaUsuarioUseCase {

    private final AlterarSenhaUsuarioGateway alterarSenhaUsuarioGateway;
    private final ConsutlarUsuarioPorIdUseCase consutlarUsuarioPorIdUseCase;

    public AlterarSenhaUsuarioUseCaseImpl(
            AlterarSenhaUsuarioGateway alterarSenhaUsuarioGateway,
            ConsutlarUsuarioPorIdUseCase consutlarUsuarioPorIdUseCase
    ) {
        this.alterarSenhaUsuarioGateway = alterarSenhaUsuarioGateway;
        this.consutlarUsuarioPorIdUseCase = consutlarUsuarioPorIdUseCase;
    }

    @Override
    public void execute(
            final Long id,
            final String senhaAtual,
            final String novaSenha,
            final String confirmaSenha
    ) {
        validarCamposObrigatorios(
                id,
                senhaAtual,
                novaSenha,
                confirmaSenha
        );

        validarConfirmarSenha(
                novaSenha,
                confirmaSenha
        );

        var usuarioExistente = consutlarUsuarioPorIdUseCase.find(id);
        if (!SenhaCriptografada.isSenhaIguais(
                senhaAtual,
                usuarioExistente.senha()
        )
        ) {
            throw new SenhaInvalidaException(
                    "A senha atual fornecida não corresponde à senha registrada em sua conta. Por favor, verifique e insira a senha correta."
            );
        }
        var usuario = usuarioExistente.alterar(
                novaSenha
        );

        alterarSenhaUsuarioGateway.execute(
                usuario
        );
    }

    private static void validarConfirmarSenha(
            String novaSenha,
            String confirmaSenha
    ) {
        if (!novaSenha.equals(confirmaSenha)) {
            throw new SenhaInvalidaException(
                    "Nova senha não confere com confirmação de senha."
            );
        }
    }


    private void validarCamposObrigatorios(
            final Long id,
            final String senhaAtual,
            final String novaSenha,
            final String confirmaSenha
    ) {
        Objects.requireNonNull(id, "O campo 'id' não pode ser nulo");
        validarNaoVazio(senhaAtual, "senhaAtual");
        validarNaoVazio(novaSenha, "novaSenha");
        validarNaoVazio(confirmaSenha, "confirmaSenha");
    }

    private void validarNaoVazio(
            final String campo,
            final String nomeCampo
    ) {
        if (campo == null || campo.trim().isEmpty()) {
            throw new SenhaInvalidaException(
                    String.format(
                            "O campo '%s' é obrigatório e não pode consistir apenas em espaços em branco.",
                            nomeCampo
                    )
            );
        }
    }
}
