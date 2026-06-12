package br.com.fiap.core.exceptions;

public class EstudanteJaExisteException extends RuntimeException {
    public EstudanteJaExisteException(String mensage){
        super(mensage);

    }
}
