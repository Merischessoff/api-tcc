package tcc.histsoc.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import tcc.histsoc.api.domain.*;

public interface HabilidadeSocialRepository extends JpaRepository<HabilidadeSocial, Long> {
    Page<HabilidadeSocial> findAll(Pageable paginacao);
}
