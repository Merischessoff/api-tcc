package tcc.histsoc.api.dto;

import jakarta.validation.constraints.NotBlank;

import tcc.histsoc.api.domain.TipoUsuario;

public record DadosCadUsuario(@NotBlank String nome, @NotBlank String cpf, @NotBlank String email, @NotBlank String senha, TipoUsuario tipo, String emailUsuarioVinculado) {}
