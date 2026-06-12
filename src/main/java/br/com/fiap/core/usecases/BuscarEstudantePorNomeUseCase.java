package br.com.fiap.core.usecases;

import br.com.fiap.core.entities.Estudante;
import br.com.fiap.core.exceptions.EstudanteNaoEncontradoException;
import br.com.fiap.core.interfaces.IEstudanteGateawy;

public class BuscarEstudantePorNomeUseCase {

    IEstudanteGateawy estudanteGateawy ;

    private BuscarEstudantePorNomeUseCase(IEstudanteGateawy estudanteGateawy) {
        this.estudanteGateawy = estudanteGateawy;
    }
    public static BuscarEstudantePorNomeUseCase create(IEstudanteGateawy estudanteGateawy) {
        return  new BuscarEstudantePorNomeUseCase(estudanteGateawy);
    }

    public Estudante run(String nome ) {
        Estudante estudante = this.estudanteGateawy.buscarPorNome(nome);

        if(estudante == null){
            throw new EstudanteNaoEncontradoException("Estudante não encontrado");
        }
        return estudante;
    }
}
