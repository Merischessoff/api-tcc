package tcc.histsoc.api.dto;
import jakarta.validation.constraints.NotNull;
import tcc.histsoc.api.domain.Usuario;
import tcc.histsoc.api.domain.TipoUsuario;

public record DadosAtualizacaoUsuario(@NotNull Long id, String nome, String email, String senha, TipoUsuario tipo){
    public DadosAtualizacaoUsuario (Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getTipo());
    }
}
