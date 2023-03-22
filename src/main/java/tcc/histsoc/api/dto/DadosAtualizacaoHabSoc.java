package tcc.histsoc.api.dto;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import tcc.histsoc.api.domain.HabilidadeSocial;
import tcc.histsoc.api.domain.HistoriaSocial;

public record DadosAtualizacaoHabSoc(@NotNull Long id, String nome, String descricao, List<HistoriaSocial> historiasSociais){
    public DadosAtualizacaoHabSoc (HabilidadeSocial habilidadeSocial){
        this(habilidadeSocial.getId(), habilidadeSocial.getNome(), habilidadeSocial.getDescricao(), habilidadeSocial.getHistoriasSociais());
    }
}
