package tcc.histsoc.api.dto;
import tcc.histsoc.api.domain.Usuario;

public record DadosAtualizacaoUsuario(String nome, String email, String senha){
    public DadosAtualizacaoUsuario (Usuario usuario){
        this(usuario.getNome(), usuario.getEmail(), usuario.getSenha());
    }
}
