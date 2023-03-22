package tcc.histsoc.api.dto;

import java.util.List;

import tcc.histsoc.api.domain.AtividadeDeVidaDiaria;
import tcc.histsoc.api.domain.HabilidadeSocial;
import tcc.histsoc.api.domain.HistoriaSocial;

public record DadosDetalhamentoHistSoc(Long id, String titulo, String texto, List<HabilidadeSocial> habilidadesSociais, List<AtividadeDeVidaDiaria> atividadesDeVidaDiaria){
    public DadosDetalhamentoHistSoc (HistoriaSocial historiaSocial){
        this(historiaSocial.getId(), historiaSocial.getTitulo(), historiaSocial.getTexto(), historiaSocial.getHabilidadesSociais(), historiaSocial.getAtividadeDeVidaDiarias());
    }
}
