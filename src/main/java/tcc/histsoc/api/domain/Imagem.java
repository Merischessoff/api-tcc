package tcc.histsoc.api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tcc.histsoc.api.dto.DadosAtualizacaoImg;
import tcc.histsoc.api.dto.DadosCadImg;

@Table(name = "imagem")
@Entity(name = "Imagem")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Imagem {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String seq;
    private String url;

    @ManyToOne
    HistoriaSocial historiaSocial;

    public Imagem(DadosCadImg dados){
        this.seq = dados.seq();
        this.url = dados.url();
    }

    public void atualizarInformacoes(DadosAtualizacaoImg dados){
        if(dados.seq() != null){
            this.seq = dados.seq();
        }
        if(dados.url() != null){
            this.url = dados.url();
        }
        if(dados.historiaSocial() != null){
            this.historiaSocial = dados.historiaSocial();
        }
    }
}
