package br.com.fiap.core.interfaces;

import br.com.fiap.core.dto.EstudanteDTO;
import br.com.fiap.core.dto.NovoEstudanteDTO;

public interface IDataSource {

    EstudanteDTO obterEstudantePorNome(String nome);

    EstudanteDTO incluirEstudante(NovoEstudanteDTO novoEstudanteDTO);
}
