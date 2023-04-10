package tcc.histsoc.api.dto;

import tcc.histsoc.api.domain.HistoriaSocial;

public record DadosListagemHistoriaSocial(Long id, String titulo, String texto) {
    public DadosListagemHistoriaSocial(HistoriaSocial historia) {
        this(historia.getId(), historia.getTitulo(), historia.getTexto());
    }
}
