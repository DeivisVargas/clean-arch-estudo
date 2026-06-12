package br.com.fiap.core.gateway;

import br.com.fiap.core.dto.EstudanteDTO;
import br.com.fiap.core.dto.NovoEstudanteDTO;
import br.com.fiap.core.entities.Estudante;
import br.com.fiap.core.exceptions.EstudanteNaoEncontradoException;
import br.com.fiap.core.interfaces.IDataSource;
import br.com.fiap.core.interfaces.IEstudanteGateawy;
import br.com.fiap.core.usecases.CadastrarEstudantesUseCase;

public class EstudanteGateway implements IEstudanteGateawy {

    private final IDataSource  dataStorageSource;

    private  EstudanteGateway(IDataSource dataStorageSource){
        this.dataStorageSource = dataStorageSource;
    }

    public static EstudanteGateway create(IDataSource dataSource){
        return new EstudanteGateway(dataSource) ;

    }



    @Override
    public Estudante buscarPorNome(String nome) {
        EstudanteDTO estudanteDTO = this.dataStorageSource.obterEstudantePorNome(nome);

        if(estudanteDTO == null){
            throw new EstudanteNaoEncontradoException("Estudante com nome " + nome + " nao encontrado");
        }

        return Estudante.create(
                estudanteDTO.identificacao(),
                estudanteDTO.nome(),
                estudanteDTO.enderecoEmail(),
                estudanteDTO.idade()

        );
    }

    @Override
    public Estudante incluir(Estudante novoEstudante) {
        final NovoEstudanteDTO novoEstudanteDTO = new NovoEstudanteDTO (
                novoEstudante.getNome(),
                novoEstudante.getEnderecoEmail() ,
                novoEstudante.getIdade()
        );

        final EstudanteDTO estudanteCriado = this.dataStorageSource.incluirEstudante(novoEstudanteDTO);

        return Estudante.create(
                estudanteCriado.identificacao(),
                estudanteCriado.nome(),
                estudanteCriado.enderecoEmail(),
                estudanteCriado.idade()
        );
    }
}
