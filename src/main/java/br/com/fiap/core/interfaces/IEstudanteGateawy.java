package br.com.fiap.core.interfaces;

import br.com.fiap.core.entities.Estudante;

public interface IEstudanteGateawy {

    Estudante buscarPorNome(String nome);

    Estudante incluir(Estudante novoEstudante);
}
