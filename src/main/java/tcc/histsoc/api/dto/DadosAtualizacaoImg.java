package tcc.histsoc.api.dto;
import jakarta.validation.constraints.NotNull;
import tcc.histsoc.api.domain.HistoriaSocial;
import tcc.histsoc.api.domain.Imagem;

public record DadosAtualizacaoImg(@NotNull Long id, String seq, String url, HistoriaSocial historiaSocial){
    public DadosAtualizacaoImg (Imagem imagem){
        this(imagem.getId(), imagem.getSeq(), imagem.getUrl(), imagem.getHistoriaSocial());
    }
}
