package br.com.wells.core.domain.usuario.model;

import br.com.wells.core.domain.usuario.exception.SenhaInvalidaException;
import lombok.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public record SenhaCriptografada(
        String valor
) {

    /**
     * @param senha
     * @return
     */
    public static SenhaCriptografada criptografar(
            final String senha
    ) {
        if (senha == null || senha.isEmpty()) {
            throw new SenhaInvalidaException("A senha não pode ser nula ou vazia");
        }

        if (senha.length() < 6) {
            throw new SenhaInvalidaException("A senha deve ter no mínimo 6 caracteres");
        }

        BCryptPasswordEncoder encoder = getBCryptPasswordEncoder();
        return new SenhaCriptografada(
                encoder.encode(senha)
        );
    }

    /**
     * @param senhaAtual
     * @param confirmarSenha
     * @return
     */
    public static boolean isSenhaIguais(
            @NonNull final String senhaAtual,
            @NonNull final String confirmarSenha
    ) {
        var bCryptPasswordEncoder = getBCryptPasswordEncoder();

        return !senhaAtual.isEmpty() &&
                bCryptPasswordEncoder.matches(
                        senhaAtual,
                        confirmarSenha
                );
    }

    private static BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
