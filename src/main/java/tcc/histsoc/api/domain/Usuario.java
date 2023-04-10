package tcc.histsoc.api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.*;
import tcc.histsoc.api.dto.DadosCadUsuario;
import tcc.histsoc.api.dto.DadosAtualizacaoUsuario;

@Table(name = "usuario")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private String emailUsuarioVinculado;

    @OneToMany
    private List<HistoriaSocial> historiasSociais = new ArrayList<>();
    

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;
    
    public Usuario(DadosCadUsuario dados){
        this.nome = dados.nome();
        this.email = dados.email();
        this.cpf = dados.cpf();
        this.senha = dados.senha();
        this.tipo = dados.tipo();
        this.emailUsuarioVinculado = dados.emailUsuarioVinculado();
    }

    public void atualizarInformacoes(DadosAtualizacaoUsuario dados){
        this.nome = dados.nome();
        this.senha = dados.senha();
    }

    private static final String ROLE_USER_LEITOR = "LEITOR";
    private static final String ROLE_USER_RESPONSAVEL = "RESPONSAVEL";
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        switch (this.tipo) {
            case LEITOR:
                return List.of(new SimpleGrantedAuthority(ROLE_USER_LEITOR));
            case RESPONSAVEL:
                return List.of(new SimpleGrantedAuthority(ROLE_USER_RESPONSAVEL));
            default:
                return List.of(new SimpleGrantedAuthority(ROLE_USER_LEITOR));
        }
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return nome;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setEmailUsuarioVinculado(String emailUsuarioVinculado) {
        this.emailUsuarioVinculado = emailUsuarioVinculado;
    }

    public String getEmailUsuarioVinculado() {
        return this.emailUsuarioVinculado;
    }
}
