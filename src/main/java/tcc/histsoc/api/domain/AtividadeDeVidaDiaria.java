package tcc.histsoc.api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tcc.histsoc.api.dto.DadosAtualizacaoAVD;
import tcc.histsoc.api.dto.DadosCadAVD;
import java.util.List;


@Table(name = "atividadedevidadiaria")
@Entity(name = "AtividadeDeVidaDiaria")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class AtividadeDeVidaDiaria{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;

    public AtividadeDeVidaDiaria(DadosCadAVD dados){
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.historiasSociais = dados.historiasSociais();
    }

    @ManyToMany
    private List<HistoriaSocial> historiasSociais;

    public void atualizarInformacoes(DadosAtualizacaoAVD dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.descricao() != null) {
            this.descricao = dados.descricao();
        }
        if(dados.historiasSociais() != null){
            this.historiasSociais = dados.historiasSociais();
        }
    }

    
}
