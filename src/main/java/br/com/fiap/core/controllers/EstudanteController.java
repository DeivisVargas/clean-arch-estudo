package br.com.fiap.core.controllers;

import br.com.fiap.core.dto.EstudanteDTO;
import br.com.fiap.core.dto.NovoEstudanteDTO;
import br.com.fiap.core.exceptions.EstudanteJaExisteException;
import br.com.fiap.core.gateway.EstudanteGateway;
import br.com.fiap.core.interfaces.IDataSource;
import br.com.fiap.core.presenters.EstudantePresenter;
import br.com.fiap.core.usecases.CadastrarEstudantesUseCase;

// faz o trabalho de orquestração da atividade
//consumido por quem está do lado de fora do COR
public class EstudanteController {

    private final IDataSource dataStorageSource;

    private EstudanteController (IDataSource iDataSource){
        this.dataStorageSource = iDataSource;

    }

    private static EstudanteController create(IDataSource iDataSource){
        return new EstudanteController(iDataSource) ;

    }


    public EstudanteDTO cadastrar(NovoEstudanteDTO novoEstudanteDTO){
        var estudanteGateway = EstudanteGateway.create(this.dataStorageSource);

        var useCase = CadastrarEstudantesUseCase.create(estudanteGateway);

        try {
            var estudante = useCase.run(novoEstudanteDTO);
            return  EstudantePresenter.toDTO(estudante);

        }catch (EstudanteJaExisteException e){
            return null ;
        }
    }

    /*
    Restante dos métodos  buscar / deletar / etc
     */

}
