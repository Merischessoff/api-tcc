package tcc.histsoc.api.domain;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

    @JsonIgnore
    @ManyToMany(mappedBy = "habilidadesSociais")
    private List<HistoriaSocial> historiasSociais;

    @JsonIgnore
    @ManyToMany(mappedBy = "habilidadesSociais")
    private List<BancoDeHistoriaSocial> historiasSociaisBanco;

    public HabilidadeSocial(DadosCadHabSoc dado){
        this.nome = dado.nome();
        this.descricao = dado.descricao();
    }
}
