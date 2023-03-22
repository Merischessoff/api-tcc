package tcc.histsoc.api.domain;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tcc.histsoc.api.dto.DadosAtualizacaoHistSoc;
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

    @ManyToMany
    private List<HabilidadeSocial> habilidadesSociais;

    @ManyToMany
    private List<AtividadeDeVidaDiaria> atividadeDeVidaDiarias;

    @OneToMany
    private List<Imagem> imagens;

    public HistoriaSocial(DadosCadHisSoc dados){
        this.titulo = dados.titulo();
        this.texto = dados.texto();
        this.atividadeDeVidaDiarias = dados.atividadeDeVidaDiarias();
        this.habilidadesSociais = dados.habilidadesSociais();
        this.imagens = dados.imagens();

    }

    public void atualizarInformacoes(DadosAtualizacaoHistSoc dados) {
        if(dados.texto()!=null){
            this.texto = dados.texto();
        }
        if(dados.titulo()!=null){
            this.titulo = dados.titulo();
        }
        if(dados.atividadeDeVidaDiarias()!=null){
            this.atividadeDeVidaDiarias = dados.atividadeDeVidaDiarias();
        }
        if(dados.habilidadeSocials()!=null){
            this.habilidadesSociais = dados.habilidadeSocials();
        }
        if(dados.imagens() != null){
            this.imagens = dados.imagens();
        }
    }
}
