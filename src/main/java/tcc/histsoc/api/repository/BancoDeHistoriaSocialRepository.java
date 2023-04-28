package tcc.histsoc.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tcc.histsoc.api.domain.BancoDeHistoriaSocial;

public interface BancoDeHistoriaRepository extends JpaRepository<BancoDeHistoriaSocial, Long>{
    
}
