package tcc.histsoc.api.dto;
import jakarta.validation.constraints.NotNull;
import tcc.histsoc.api.domain.Usuario;

public record DadosAtualizacaoUsuario(@NotNull Long id, String nome, String cpf, String email, String senha){
    public DadosAtualizacaoUsuario (Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getCpf(), usuario.getEmail(), usuario.getSenha());
    }
}
