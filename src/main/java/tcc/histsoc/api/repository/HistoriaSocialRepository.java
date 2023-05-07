package tcc.histsoc.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import tcc.histsoc.api.domain.*;

public interface HistoriaSocialRepository extends JpaRepository<HistoriaSocial, Long> {
    Page<HistoriaSocial> findAll(Pageable paginacao);

    @Query(value = "SELECT * FROM historiasocial WHERE email_usuario_responsavel = ?1", nativeQuery = true)
    List<HistoriaSocial> findByAllEmailHistoriasProprias(String email);

    @Query(value = "SELECT * FROM historiasocial h WHERE h.id NOT IN (SELECT IFNULL(hul.historia_social_id, 0) FROM usuario u LEFT OUTER JOIN historiasocial_usuarios_leitores hul on u.id = hul.usuarios_leitores_id WHERE u.email = ?1)", nativeQuery = true)
    List<HistoriaSocial> findByAllEmailHistoriaUsuarioAssociado(String emailLeitor);

    @Query(value = "SELECT * FROM historiasocial h WHERE h.id IN (SELECT IFNULL(hul.historia_social_id, 0) FROM usuario u LEFT OUTER JOIN historiasocial_usuarios_leitores hul on u.id = hul.usuarios_leitores_id WHERE u.email = ?1)", nativeQuery = true)
    List<HistoriaSocial> findByAllEmailHistoriaUsuarioDesassociado(String emailLeitor);

    @Modifying
    @Query(value = "INSERT INTO historiasocial_usuarios_leitores (usuarios_leitores_id, historia_social_id) VALUES(?1, ?2) ", nativeQuery = true)
    void associarUsuarioHistorias(Long idUsuario, Long idHistoria);

    @Modifying
    @Query(value = "DELETE FROM historiasocial_usuarios_leitores WHERE usuarios_leitores_id = ?1 AND historia_social_id = ?2", nativeQuery = true)
    void desassociarUsuarioHistorias(Long idUsuario, Long idHistoria);

}

