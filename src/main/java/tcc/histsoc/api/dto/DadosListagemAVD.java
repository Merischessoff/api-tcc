package tcc.histsoc.api.dto;

import java.util.List;

import tcc.histsoc.api.domain.AtividadeDeVidaDiaria;
import tcc.histsoc.api.domain.HistoriaSocial;

public record DadosListagemAVD (Long id, String nome, String descricao, List<HistoriaSocial> historiasSociais){

    public DadosListagemAVD(AtividadeDeVidaDiaria atividadeDeVidaDiaria) {
        this(atividadeDeVidaDiaria.getId(),
            atividadeDeVidaDiaria.getNome(),
            atividadeDeVidaDiaria.getDescricao(), 
            atividadeDeVidaDiaria.getHistoriasSociais());
    }
}
