package tcc.histsoc.api.dto;

import java.util.List;

import tcc.histsoc.api.domain.AtividadeDeVidaDiaria;
import tcc.histsoc.api.domain.HabilidadeSocial;
import tcc.histsoc.api.domain.HistoriaSocial;
import tcc.histsoc.api.domain.Imagem;

public record DadosListagemHistoriaSocial(Long id, String titulo, String texto, String emailUsuarioResponsavel, List<AtividadeDeVidaDiaria> atividadesDeVidaDiarias, List<HabilidadeSocial> habilidadesSociais, List<Imagem>imagens) {
    public DadosListagemHistoriaSocial(HistoriaSocial historia) {
        this(historia.getId(), historia.getTitulo(), historia.getTexto(), historia.getEmailUsuarioResponsavel(), historia.getAtividadesDeVidaDiarias(), historia.getHabilidadesSociais(), historia.getImagens());
    }
}
