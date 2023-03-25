package tcc.histsoc.api.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import tcc.histsoc.api.domain.AtividadeDeVidaDiaria;
import tcc.histsoc.api.domain.HabilidadeSocial;
import tcc.histsoc.api.domain.Imagem;

public record DadosCadHisSoc (
    @NotBlank String titulo,
    @NotBlank String texto, List<HabilidadeSocial> habilidadesSociais, List<AtividadeDeVidaDiaria> atividadeDeVidaDiarias, List<Imagem> imagens){}

