package tcc.histsoc.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import tcc.histsoc.api.domain.BancoDeHistoriaSocial;

public interface BancoDeHistoriaSocialRepository extends JpaRepository<BancoDeHistoriaSocial, Long> {
    
    @Query(value = "SELECT * FROM bancodehistoria bh WHERE bh.id NOT IN (SELECT IFNULL(hul.banco_de_historia_id, 0) FROM usuario u LEFT OUTER JOIN bancodehistoria_usuarios_leitores hul on u.id = hul.usuarios_leitores_id WHERE u.email = ?1)", nativeQuery = true)
    List<BancoDeHistoriaSocial> findByAllEmailBancoDeHistoriaUsuarioNotIn(String emailLeitor);

    @Query(value = "SELECT * FROM bancodehistoria bh WHERE bh.id IN (SELECT IFNULL(hul.banco_de_historia_id, 0) FROM usuario u LEFT OUTER JOIN bancodehistoria_usuarios_leitores hul on u.id = hul.usuarios_leitores_id WHERE u.email = ?1)", nativeQuery = true)
    List<BancoDeHistoriaSocial> findByAllEmailBancoDeHistoriaUsuarioIn(String emailLeitor);

    @Modifying
    @Query(value = "INSERT INTO bancodehistoria_usuarios_leitores (usuarios_leitores_id, banco_de_historia_id) VALUES(?1, ?2) ", nativeQuery = true)
    void associarUsuarioBancoDeHistorias(Long idUsuario, Long idBancoDeHistoria);

    @Modifying
    @Query(value = "DELETE FROM bancodehistoria_usuarios_leitores WHERE usuarios_leitores_id = ?1 AND banco_de_historia_id = ?2", nativeQuery = true)
    void desassociarUsuarioBancoDeHistorias(Long idUsuario, Long idBancoDeHistoria);
}