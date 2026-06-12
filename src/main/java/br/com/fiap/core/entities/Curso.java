package br.com.fiap.core.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.security.InvalidParameterException;
import java.util.ArrayList;

@Getter
@Setter
@EqualsAndHashCode
public class Curso {
    private String nome;
    private boolean ativo;
    private ArrayList<Estudante> estudantes = new ArrayList<>();


    private Boolean nomeValido(String nome){
        if(nome.trim().isEmpty()){
            throw new InvalidParameterException("Nome não pode ser vazio");
        }
        return true;
    }

    public Curso(String nome, boolean ativo) {

        this.nomeValido(nome);

        this.nome = nome;
        this.ativo = ativo;
    }
}
