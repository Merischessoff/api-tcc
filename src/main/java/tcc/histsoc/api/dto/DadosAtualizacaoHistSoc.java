package tcc.histsoc.api.dto;

import java.util.List;

import tcc.histsoc.api.domain.AtividadeDeVidaDiaria;
import tcc.histsoc.api.domain.HabilidadeSocial;
import tcc.histsoc.api.domain.HistoriaSocial;
import tcc.histsoc.api.domain.Imagem;
import tcc.histsoc.api.domain.Usuario;

public record DadosAtualizacaoHistSoc (String titulo, String texto, String emailUsuarioResponsavel, List<HabilidadeSocial> habilidadesSociais, List<AtividadeDeVidaDiaria> atividadesDeVidaDiarias, List<Imagem> imagens, List<Usuario> usuariosLeitores){
    public DadosAtualizacaoHistSoc(HistoriaSocial historiaSocial){
        this(historiaSocial.getTexto(), historiaSocial.getEmailUsuarioResponsavel(), historiaSocial.getTitulo(), historiaSocial.getHabilidadesSociais(), historiaSocial.getAtividadesDeVidaDiarias(), historiaSocial.getImagens(), historiaSocial.getUsuariosLeitores());
    }
}
