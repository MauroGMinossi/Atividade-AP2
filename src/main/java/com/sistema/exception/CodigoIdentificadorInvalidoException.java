package com.sistema.exception;

public class CodigoIdentificadorInvalidoException extends RuntimeException {
    public CodigoIdentificadorInvalidoException(String mensagem) {
        super(mensagem);
    }
}