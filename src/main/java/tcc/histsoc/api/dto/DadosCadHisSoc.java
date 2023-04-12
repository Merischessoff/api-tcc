package tcc.histsoc.api.dto;


import java.util.List;

import jakarta.validation.constraints.NotBlank;
import tcc.histsoc.api.domain.AtividadeDeVidaDiaria;
import tcc.histsoc.api.domain.HabilidadeSocial;
import tcc.histsoc.api.domain.Imagem;
import tcc.histsoc.api.domain.Usuario;

public record DadosCadHisSoc (@NotBlank String titulo, @NotBlank String texto, @NotBlank String emailUsuarioResponsavel, List<HabilidadeSocial> habilidadesSociais, List<AtividadeDeVidaDiaria> atividadesDeVidaDiarias, List<Imagem> imagens, List<Usuario> usuariosLeitores){}

