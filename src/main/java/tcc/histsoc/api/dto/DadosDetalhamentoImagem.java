package tcc.histsoc.api.dto;

import tcc.histsoc.api.domain.Imagem;

public record DadosDetalhamentoImagem(int seq, String url, String texto) {
    public DadosDetalhamentoImagem (Imagem imagem){
        this(imagem.getSeq(), imagem.getUrl(), imagem.getTexto());
    }
}
