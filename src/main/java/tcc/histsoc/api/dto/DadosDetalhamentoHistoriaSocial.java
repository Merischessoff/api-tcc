package tcc.histsoc.api.dto;

import java.util.List;

import tcc.histsoc.api.domain.AtividadeDeVidaDiaria;
import tcc.histsoc.api.domain.HabilidadeSocial;
import tcc.histsoc.api.domain.HistoriaSocial;
import tcc.histsoc.api.domain.Imagem;

public record DadosDetalhamentoHistoriaSocial(String titulo, String texto, String emailUsuarioResponsavel, AtividadeDeVidaDiaria avd, HabilidadeSocial hs, List<Imagem>imagens) {
    public DadosDetalhamentoHistoriaSocial(HistoriaSocial historia){
        this(historia.getTitulo(), historia.getTexto(), historia.getEmailUsuarioResponsavel(), historia.getAtividadeDeVidaDiaria(), historia.getHabilidadeSocial(), historia.getImagens());
    }
}
