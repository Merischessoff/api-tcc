package tcc.histsoc.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tcc.histsoc.api.domain.BancoDeHistoriaSocial;

public interface BancoDeHistoriaSocialRepository extends JpaRepository<BancoDeHistoriaSocial, Long>{
    
}
