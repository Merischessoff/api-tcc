package tcc.histsoc.api.domain;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tcc.histsoc.api.dto.DadosCadHisSoc;

@Table(name = "historiasocial")
@Entity(name = "HistoriaSocial")
@Getter
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

    @ManyToOne
    @JoinColumn(name = "emailUsuarioLeitor")
    private Usuario usuarioLeitor;

    @ManyToOne(cascade = CascadeType.ALL)
    private HabilidadeSocial habilidadeSocial;

    @ManyToOne(cascade = CascadeType.ALL)
    private AtividadeDeVidaDiaria atividadeDeVidaDiaria;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Imagem> imagens;

    public HistoriaSocial(DadosCadHisSoc dado){
        this.habilidadeSocial = dado.habilidadeSocial();
        this.atividadeDeVidaDiaria = dado.atividadeDeVidaDiaria();
        this.imagens = dado.imagens();
        this.titulo = dado.titulo();
        this.texto = dado.texto();
        this.emailUsuarioResponsavel = dado.emailUsuarioResponsavel();
    }



  
}
