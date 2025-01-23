package tcc.histsoc.api.dto;

import java.util.List;

import tcc.histsoc.api.domain.AtividadeDeVidaDiaria;
import tcc.histsoc.api.domain.BancoDeHistoriaSocial;
import tcc.histsoc.api.domain.HabilidadeSocial;
import tcc.histsoc.api.domain.Imagem;

public record DadosListagemBancoDeHistoriaSocial(Long id, String titulo, String texto, List<AtividadeDeVidaDiaria> atividadesDeVidaDiarias, List<HabilidadeSocial> habilidadesSociais, List<Imagem>imagens) {
    public DadosListagemBancoDeHistoriaSocial(BancoDeHistoriaSocial historia) {
        this(historia.getId(), historia.getTitulo(), historia.getTexto(), historia.getAtividadesDeVidaDiarias(), historia.getHabilidadesSociais(), historia.getImagens());
    }
}