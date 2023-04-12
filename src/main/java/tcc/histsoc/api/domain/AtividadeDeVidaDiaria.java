package tcc.histsoc.api.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tcc.histsoc.api.dto.DadosCadAVD;


@Table(name = "atividadedevidadiaria")
@Entity(name = "AtividadeDeVidaDiaria")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Setter
public class AtividadeDeVidaDiaria{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;

    @JsonIgnore
    @ManyToMany(mappedBy = "atividadesDeVidaDiarias")
    private List<HistoriaSocial> historiasSociais;

    
}
