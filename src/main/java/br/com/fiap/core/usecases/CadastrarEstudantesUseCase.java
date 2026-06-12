package br.com.fiap.core.usecases;

import br.com.fiap.core.dto.NovoEstudanteDTO;
import br.com.fiap.core.entities.Estudante;
import br.com.fiap.core.exceptions.EstudanteJaExisteException;
import br.com.fiap.core.interfaces.IEstudanteGateawy;

public class CadastrarEstudantesUseCase {

    IEstudanteGateawy estudanteGateawy ;

    protected CadastrarEstudantesUseCase(IEstudanteGateawy estudanteGateawy) {
        this.estudanteGateawy = estudanteGateawy;
    }
    public static CadastrarEstudantesUseCase create(IEstudanteGateawy estudanteGateawy) {
        return  new CadastrarEstudantesUseCase(estudanteGateawy);

    }

    public Estudante run(NovoEstudanteDTO novoEstudanteDTO) {
        // verificar se existe o estudante
        final Estudante estudanteExistente = this.estudanteGateawy.buscarPorNome(novoEstudanteDTO.nome()) ;
        if(estudanteExistente != null){
            throw new EstudanteJaExisteException("Estudante "+ novoEstudanteDTO.nome() + " já está cadastrado ") ;

        }
        Estudante novoEstudante =  Estudante.create( novoEstudanteDTO.nome()  ,
                novoEstudanteDTO.enderecoEmail() ,
                novoEstudanteDTO.idade()
        );
        return this.estudanteGateawy.incluir(novoEstudante);
    }


}
