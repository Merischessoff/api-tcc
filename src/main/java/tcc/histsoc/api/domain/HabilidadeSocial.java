package tcc.histsoc.api.domain;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tcc.histsoc.api.dto.DadosAtualizacaoHabSoc;
import tcc.histsoc.api.dto.DadosCadHabSoc;

@Table(name = "habilidadesocial")
@Entity(name = "HabilidadeSocial")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class HabilidadeSocial {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;

    @ManyToMany
    private List<HistoriaSocial> historiasSociais;

    public HabilidadeSocial(DadosCadHabSoc dados){
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.historiasSociais = dados.historiasSociais();
    }

    public void atualizarInformacoes(DadosAtualizacaoHabSoc dados){
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.descricao() != null){
            this.descricao = dados.descricao();
        }
        if(dados.historiasSociais()!=null){
            this.historiasSociais = dados.historiasSociais();
        }
    }

}
