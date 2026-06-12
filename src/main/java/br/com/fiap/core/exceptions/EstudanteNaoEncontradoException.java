package br.com.fiap.core.exceptions;

public class EstudanteNaoEncontradoException extends RuntimeException {
    public EstudanteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
