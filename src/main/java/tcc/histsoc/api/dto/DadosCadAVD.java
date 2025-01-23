package tcc.histsoc.api.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import tcc.histsoc.api.domain.HistoriaSocial;

public record DadosCadAVD(Long id, @NotBlank String nome, @NotBlank String descricao, @NotBlank List<HistoriaSocial> historias) {}
