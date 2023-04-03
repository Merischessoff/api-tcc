package tcc.histsoc.api.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import tcc.histsoc.api.domain.HistoriaSocial;

public record DadosCadHabSoc (
    @NotBlank String nome,
    @NotBlank String descricao, HistoriaSocial historiaSocial){}
