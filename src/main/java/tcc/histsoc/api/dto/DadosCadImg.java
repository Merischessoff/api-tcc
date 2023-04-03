package tcc.histsoc.api.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosCadImg(@NotBlank int seq, @NotBlank String url, @NotBlank String texto) {}
