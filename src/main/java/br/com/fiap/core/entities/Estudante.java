package br.com.fiap.core.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.validator.EmailValidator;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class Estudante {
    @Setter
    private String identificacaoInterna;
    private String nome;
    private String enderecoEmail;
    private int idade ;



    private static void idadeValida(int idade){
        if(idade < 18){
            throw new IllegalArgumentException("Idade inválida");
        }

    }

    private static void validarEmail(String email){

        EmailValidator emailValidator = EmailValidator.getInstance();
        if(!emailValidator.isValid(email)){
            throw new IllegalArgumentException("Email  inválido");
        }

    }

    public static Estudante create(String nome , String enderecoEmail, int idade) throws IllegalArgumentException{
        if(nome == null || enderecoEmail == null){
            throw new IllegalArgumentException("faltam dados");
        }
        validarEmail(enderecoEmail);
        idadeValida(idade);

        Estudante estudante = new Estudante();
        estudante.setNome(nome);
        estudante.setEnderecoEmail(enderecoEmail);
        estudante.setIdade(idade);

        return estudante;

    }

    public static Estudante create(String identificacaoInterna, String nome , String enderecoEmail, int idade) throws IllegalArgumentException{
        if(nome == null || enderecoEmail == null){
            throw new IllegalArgumentException("faltam dados");
        }
        validarEmail(enderecoEmail);
        idadeValida(idade);

        Estudante estudante = new Estudante();
        estudante.setNome(nome);
        estudante.setEnderecoEmail(enderecoEmail);
        estudante.setIdade(idade);
        estudante.setIdentificacaoInterna(identificacaoInterna);


        return estudante;

    }


    public Estudante(String identificacaoInterna, String nome, String enderecoEmail, int idade) {
        validarEmail(enderecoEmail);
        idadeValida(idade);

        this.identificacaoInterna = identificacaoInterna;
        this.nome = nome;
        this.enderecoEmail = enderecoEmail;
        this.idade = idade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEnderecoEmail(String enderecoEmail) {
        validarEmail(enderecoEmail);
        this.enderecoEmail = enderecoEmail;
    }

    public void setIdade(int idade) {
        idadeValida(idade);
        this.idade = idade;
    }
}
