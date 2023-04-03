package tcc.histsoc.api.dto;

import java.util.List;

import tcc.histsoc.api.domain.AtividadeDeVidaDiaria;
import tcc.histsoc.api.domain.HistoriaSocial;

public record DadosDetalhamentoAVD(Long id, String nome, String descricao, HistoriaSocial historiaSocial){
    public DadosDetalhamentoAVD (AtividadeDeVidaDiaria atividadeDeVidaDiaria){
        this(atividadeDeVidaDiaria.getId(), atividadeDeVidaDiaria.getNome(), atividadeDeVidaDiaria.getDescricao(), atividadeDeVidaDiaria.getHistoriaSocial());
    }
}