package tcc.histsoc.api.dto;

import java.util.List;

import tcc.histsoc.api.domain.AtividadeDeVidaDiaria;
import tcc.histsoc.api.domain.HistoriaSocial;

public record DadosListagemAVD (Long id, String nome, String descricao, HistoriaSocial historiaSocial){

    public DadosListagemAVD(AtividadeDeVidaDiaria atividadeDeVidaDiaria) {
        this(atividadeDeVidaDiaria.getId(),
            atividadeDeVidaDiaria.getNome(),
            atividadeDeVidaDiaria.getDescricao(), 
            atividadeDeVidaDiaria.getHistoriaSocial());
    }
}
