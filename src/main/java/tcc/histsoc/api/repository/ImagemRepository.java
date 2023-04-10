package tcc.histsoc.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tcc.histsoc.api.domain.*;

public interface ImagemRepository extends JpaRepository<Imagem, Long> {
    Page<Imagem> findAll(Pageable paginacao);

    @Query(value = "SELECT * FROM imagem LEFT JOIN historiasocial_imagem ON historiasocial_imagem.historia_social_id = historiasocial.id LEFT JOIN imagem ON imagem.id = historiasocial_imagem.imagem_id WHERE historiasocial.email_usuario_responsavel = ?1 and historiasocial.id = ?2", nativeQuery = true)
    List<Imagem> findByAllEmailIdImagens(String email, Long id);
   
    
}
