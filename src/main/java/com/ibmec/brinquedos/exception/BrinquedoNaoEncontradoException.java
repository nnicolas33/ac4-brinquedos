package com.ibmec.brinquedos.exception;

public class BrinquedoNaoEncontradoException extends RuntimeException {
    public BrinquedoNaoEncontradoException(Long id) {
        super("Brinquedo não encontrado: " + id);
    }
}