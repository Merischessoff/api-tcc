package tcc.histsoc.api.dto;
import tcc.histsoc.api.domain.Usuario;

public record DadosAtualizacaoUsuario(String nome, String senha){
    public DadosAtualizacaoUsuario (Usuario usuario){
        this(usuario.getNome(), usuario.getSenha());
    }
}
