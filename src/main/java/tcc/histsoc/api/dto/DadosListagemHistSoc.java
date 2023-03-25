package tcc.histsoc.api.dto;

import java.util.List;

import tcc.histsoc.api.domain.AtividadeDeVidaDiaria;
import tcc.histsoc.api.domain.HabilidadeSocial;
import tcc.histsoc.api.domain.HistoriaSocial;

public record DadosListagemHistSoc (Long id, String titulo, String texto, List<AtividadeDeVidaDiaria> atividadesDeVidaDiaria, List<HabilidadeSocial> habilidadesDeVidaDiaria){

    public DadosListagemHistSoc(HistoriaSocial historiaSocial) {
        this(historiaSocial.getId(), historiaSocial.getTitulo(), historiaSocial.getTexto(), historiaSocial.getAtividadeDeVidaDiarias(), historiaSocial.getHabilidadesSociais());
    }
}
