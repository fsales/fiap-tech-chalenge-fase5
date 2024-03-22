package br.com.wells.core.domain.usuario.exception;

public class PagamentoInvalidoException extends  RuntimeException {
    public  PagamentoInvalidoException(String message) {
        super(message);
    }
}
