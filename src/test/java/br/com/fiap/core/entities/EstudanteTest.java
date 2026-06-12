package br.com.fiap.core.entities;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstudanteTest {

    // 1. Declarar as variáveis como atributos da classe de teste
    private String nome;
    private String email;
    private int idade;
    private String  identificacao ;

    // 2. Inicializar os valores antes de CADA teste rodar
    // arrange
    @BeforeEach
    void setUp() {
        nome = "Deivis";
        email = "deivisvp@yahoo.com";
        idade = 37;
        identificacao = "123";

    }

    @DisplayName("Cria estudante com sucesso")
    @Test
    void testeEstudanteOk() {
        // act
        var estudante =  Estudante.create(nome , email , idade ) ;

        //assert
        assertEquals(nome, estudante.getNome());
        assertEquals(email, estudante.getEnderecoEmail());
        assertEquals(idade, estudante.getIdade());
        assertNull(estudante.getIdentificacaoInterna());

    }

    @DisplayName("Cria estudante com sucesso")
    @Test
    void testeEstudanteComIdentificacaoInternaOk() {

        var estudante =  Estudante.create(identificacao , nome , email , idade ) ;

        assertEquals(nome, estudante.getNome());
        assertEquals(email, estudante.getEnderecoEmail());
        assertEquals(idade, estudante.getIdade());
        assertEquals(identificacao, estudante.getIdentificacaoInterna());


    }

    @DisplayName("Cria estudante com sucesso pelo construtor completo")
    @Test
    void testeConstrutorCompleto() {
        var estudante = new Estudante("ID-123", "Deivis", "deivisvp@yahoo.com", 37);
        assertEquals("ID-123", estudante.getIdentificacaoInterna());
        assertEquals("Deivis", estudante.getNome());
    }

    @DisplayName("Deve lançar exceção quando faltarem dados no create")
    @Test
    void testeCreateFaltandoDados() {
        assertThrows(IllegalArgumentException.class, () -> {
            Estudante.create(null, "email@teste.com", 20);
        }, "faltam dados");

        assertThrows(IllegalArgumentException.class, () -> {
            Estudante.create("Nome", null, 20);
        }, "faltam dados");
    }

    @DisplayName("Deve lançar exceção quando faltarem dados no create com construtor completo")
    @Test
    void testeCreateFaltandoDadosClasseCompleta() {
        assertThrows(IllegalArgumentException.class, () -> {
            Estudante.create(identificacao,null, "email@teste.com", 20);
        }, "faltam dados");

        assertThrows(IllegalArgumentException.class, () -> {
            Estudante.create(identificacao, "Nome", null, 20);
        }, "faltam dados");
    }



    @DisplayName("Deve lançar exceção para e-mail inválido")
    @Test
    void testeEmailInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            Estudante.create("Deivis", "email_invalido", 37);
        }, "Email inválido");
    }

    @DisplayName("Deve lançar exceção para idade menor ou igual a 18")
    @Test
    void testeIdadeInvalida() {
        assertThrows(IllegalArgumentException.class, () -> {
            Estudante.create("Deivis", "deivisvp@yahoo.com", 17);
        }, "Idade inválida");
    }



}