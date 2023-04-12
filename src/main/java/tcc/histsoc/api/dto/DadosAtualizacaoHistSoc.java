package tcc.histsoc.api.dto;

import java.util.List;

import tcc.histsoc.api.domain.AtividadeDeVidaDiaria;
import tcc.histsoc.api.domain.HabilidadeSocial;
import tcc.histsoc.api.domain.HistoriaSocial;
import tcc.histsoc.api.domain.Imagem;

public record DadosAtualizacaoHistSoc (Long id, String texto, String titulo, List<HabilidadeSocial> habilidadesSociais, List<AtividadeDeVidaDiaria> atividadesDeVidaDiarias, List<Imagem> imagem){
    public DadosAtualizacaoHistSoc(HistoriaSocial historiaSocial){
        this(historiaSocial.getId(), historiaSocial.getTexto(), historiaSocial.getTitulo(), historiaSocial.getHabilidadesSociais(), historiaSocial.getAtividadesDeVidaDiarias(), historiaSocial.getImagens());
    }
}
