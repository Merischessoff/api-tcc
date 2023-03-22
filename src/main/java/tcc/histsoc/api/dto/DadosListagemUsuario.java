package tcc.histsoc.api.dto;

import tcc.histsoc.api.domain.Usuario;
import tcc.histsoc.api.domain.TipoUsuario;


public record DadosListagemUsuario (Long id, String nome, String email, String senha, TipoUsuario tipo){

    public DadosListagemUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getTipo());
    }
}
