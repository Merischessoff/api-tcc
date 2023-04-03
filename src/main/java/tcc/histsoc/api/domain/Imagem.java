package tcc.histsoc.api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private int seq;
    private String url;
    private String texto;


    @ManyToOne
    HistoriaSocial historiaSocial;


}
