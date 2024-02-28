package br.com.fsales.wells.core.domain.usuario.model;

import br.com.fsales.wells.core.domain.usuario.exception.SenhaInvalidaException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public record SenhaCriptografada(String valor) {

    public static SenhaCriptografada criptografar(String senha) {
        if (senha == null || senha.isEmpty()) {
            throw new SenhaInvalidaException("A senha não pode ser nula ou vazia");
        }

        if (senha.length() < 6) {
            throw new SenhaInvalidaException("A senha deve ter no mínimo 6 caracteres");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return new SenhaCriptografada(encoder.encode(senha));
    }
}
