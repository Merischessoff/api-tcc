package tcc.histsoc.api.dto;

import tcc.histsoc.api.domain.HistoriaSocial;
import tcc.histsoc.api.domain.Imagem;

public record DadosListagemImagem (Long id, String seq, String url, HistoriaSocial historiaSocial){

    public DadosListagemImagem(Imagem imagem) {
        this(imagem.getId(), imagem.getSeq(), imagem.getUrl(), imagem.getHistoriaSocial());
    }
}
