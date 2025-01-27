package tcc.histsoc.api.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tcc.histsoc.api.dto.DadosAtualizacaoHistSoc;
import tcc.histsoc.api.dto.DadosCadHisSoc;

@Table(name = "historiasocial")
@Entity(name = "HistoriaSocial")
@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class HistoriaSocial {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String texto;
    private String emailUsuarioResponsavel;

    @JsonIgnore
    @ManyToMany
    private List<Usuario> usuariosLeitores;

    @ManyToMany
    private List<HabilidadeSocial> habilidadesSociais;

    @ManyToMany
    private List<AtividadeDeVidaDiaria> atividadesDeVidaDiarias;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Imagem> imagens;

    public HistoriaSocial(DadosCadHisSoc dado){
        this.habilidadesSociais = dado.habilidadesSociais();
        this.atividadesDeVidaDiarias = dado.atividadesDeVidaDiarias();
        this.imagens = dado.imagens();
        this.titulo = dado.titulo();
        this.texto = dado.texto();
        this.emailUsuarioResponsavel = dado.emailUsuarioResponsavel();
    }

    public void atualizaHistoriaSocial(DadosAtualizacaoHistSoc dado){
        this.habilidadesSociais = dado.habilidadesSociais();
        this.atividadesDeVidaDiarias = dado.atividadesDeVidaDiarias();
        this.imagens = dado.imagens();
        this.titulo = dado.titulo();
        this.texto = dado.texto();
    }
  
}
