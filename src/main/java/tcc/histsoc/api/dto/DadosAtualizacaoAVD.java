package tcc.histsoc.api.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import tcc.histsoc.api.domain.AtividadeDeVidaDiaria;
import tcc.histsoc.api.domain.HistoriaSocial;

public record DadosAtualizacaoAVD(@NotNull Long id, String nome, String descricao, List<HistoriaSocial> historiasSociais){
    public DadosAtualizacaoAVD (AtividadeDeVidaDiaria atividadeDeVidaDiaria){
        this(atividadeDeVidaDiaria.getId(), atividadeDeVidaDiaria.getNome(), atividadeDeVidaDiaria.getDescricao(), atividadeDeVidaDiaria.getHistoriasSociais());
    }
}
