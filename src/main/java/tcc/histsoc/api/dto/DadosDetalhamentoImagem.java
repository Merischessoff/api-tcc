package tcc.histsoc.api.dto;

import tcc.histsoc.api.domain.HistoriaSocial;
import tcc.histsoc.api.domain.Imagem;

public record DadosDetalhamentoImagem(Long id, String seq, String url, HistoriaSocial historiaSocial){
    public DadosDetalhamentoImagem (Imagem imagem){
        this(imagem.getId(), imagem.getSeq(), imagem.getUrl(), imagem.getHistoriaSocial());
    }
}
