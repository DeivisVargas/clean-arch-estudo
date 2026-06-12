package br.com.fiap.core.presenters;

import br.com.fiap.core.dto.EstudanteDTO;
import br.com.fiap.core.entities.Estudante;

// MAPPER
public class EstudantePresenter {

    public static EstudanteDTO toDTO(Estudante estudante) {

        final String identificacao = estudante.getIdentificacaoInterna();
        final String identificacaoOsuscada = identificacao.charAt(1) + "...." + identificacao.charAt(identificacao.length() - 1);

        return  new EstudanteDTO(
                identificacaoOsuscada ,
                estudante.getNome(),
                estudante.getEnderecoEmail(),
                estudante.getIdade()
        );
    }
}
