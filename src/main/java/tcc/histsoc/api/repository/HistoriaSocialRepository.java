package tcc.histsoc.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tcc.histsoc.api.domain.*;

public interface HistoriaSocialRepository extends JpaRepository<HistoriaSocial, Long> {
    Page<HistoriaSocial> findAll(Pageable paginacao);

    @Query(value = "SELECT * FROM historiasocial WHERE email_usuario_responsavel = ?1", nativeQuery = true)
    List<HistoriaSocial> findByAllEmailHistoriasProprias(String email);
}

