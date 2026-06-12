package br.com.fiap.core.usecases;

import br.com.fiap.core.dto.NovoEstudanteDTO;
import br.com.fiap.core.entities.Estudante;
import br.com.fiap.core.exceptions.EstudanteJaExisteException;
import br.com.fiap.core.interfaces.IEstudanteGateawy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CadastrarEstudantesUseCaseTest {

    @Mock
    private IEstudanteGateawy estudanteGateway;

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


    @DisplayName("Cadastro com sucesso")
    @Test
    void testRegistradoOK(){
        // arrange

        IEstudanteGateawy estudanteGateawy = mock(IEstudanteGateawy.class);

        //comportamento
        when(estudanteGateawy.buscarPorNome(anyString())).thenReturn(
                null
        );
        when(estudanteGateawy.incluir(any())).thenReturn(
                Estudante.create("123" , nome,email,idade)
        );

        //act
        final Estudante estudante = CadastrarEstudantesUseCase.create(estudanteGateawy).run(
                new NovoEstudanteDTO(nome, email, idade)
        );

        //assert

        assertNotNull(estudante);
        assertEquals(nome, estudante.getNome());
        assertEquals(email, estudante.getEnderecoEmail());
        assertEquals(identificacao, estudante.getIdentificacaoInterna());

    }

    @DisplayName("Deve lançar exceção quando o estudante já existir")
    @Test
    void testeEstudanteJaExiste() {
        // 1. Criando o mock local com o nome correto e limpo (gateway)
        IEstudanteGateawy gateway = mock(IEstudanteGateawy.class);

        // 2. Passando o mock correto para o Use Case
        var useCase = new CadastrarEstudantesUseCase(gateway);

        String nomeRepetido = "Deivis";
        var dto = new NovoEstudanteDTO(nomeRepetido, "deivisvp@yahoo.com", 37);
        var estudanteFakeQueJaExiste = Estudante.create(nomeRepetido, "outroemail@teste.com", 40);

        // 3. Usando a variável 'gateway' que acabamos de criar e instanciar
        when(gateway.buscarPorNome(nomeRepetido)).thenReturn(estudanteFakeQueJaExiste);

        // 4. ACT & ASSERT
        EstudanteJaExisteException excecao = assertThrows(EstudanteJaExisteException.class, () -> {
            useCase.run(dto);
        });

        String mensagemEsperada = "Estudante " + nomeRepetido + " já está cadastrado ";
        assertEquals(mensagemEsperada, excecao.getMessage());

        // 5. Verificando no mock correto
        verify(gateway, never()).incluir(any());
    }

}