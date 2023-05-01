package tcc.histsoc.api.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "bancodehistoria")
@Entity(name = "BancoDeHistoria")
@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class BancoDeHistoriaSocial {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String texto;

    @JsonIgnore
    @ManyToMany
    private List<Usuario> usuariosLeitores;
    
    @ManyToMany
    private List<HabilidadeSocial> habilidadesSociais;

    @ManyToMany
    private List<AtividadeDeVidaDiaria> atividadesDeVidaDiarias;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Imagem> imagens;
}
