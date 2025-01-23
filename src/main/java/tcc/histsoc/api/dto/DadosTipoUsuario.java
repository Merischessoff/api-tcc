package tcc.histsoc.api.dto;

import tcc.histsoc.api.domain.TipoUsuario;
import tcc.histsoc.api.domain.Usuario;

public record DadosTipoUsuario(TipoUsuario tipo) {
    public DadosTipoUsuario(Usuario usuario){
        this(usuario.getTipo());
    }

}
