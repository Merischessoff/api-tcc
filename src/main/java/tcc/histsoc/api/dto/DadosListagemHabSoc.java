package tcc.histsoc.api.dto;

import java.util.List;

import tcc.histsoc.api.domain.HabilidadeSocial;
import tcc.histsoc.api.domain.HistoriaSocial;

public record DadosListagemHabSoc (Long id, String nome, String descricao, HistoriaSocial historiaSocial){

    public DadosListagemHabSoc(HabilidadeSocial habilidadeSocial) {
        this(habilidadeSocial.getId(), habilidadeSocial.getNome(), habilidadeSocial.getDescricao(), habilidadeSocial.getHistoriaSocial());
    }
}
