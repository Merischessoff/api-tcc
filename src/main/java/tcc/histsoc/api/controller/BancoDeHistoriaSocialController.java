package tcc.histsoc.api.controller;

import tcc.histsoc.api.domain.BancoDeHistoriaSocial;
import tcc.histsoc.api.dto.DadosListagemBancoDeHistoriaSocial;
import tcc.histsoc.api.repository.BancoDeHistoriaSocialRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("bancodehistoriasocial")
public class BancoDeHistoriaSocialController {

    @Autowired
    private BancoDeHistoriaSocialRepository repositoryHistSoc;

    
    @GetMapping
    public ResponseEntity<List<DadosListagemBancoDeHistoriaSocial>> listarBancoDeHistoriasSociais() {
        List<BancoDeHistoriaSocial> historias = repositoryHistSoc.findAll();
        List<DadosListagemBancoDeHistoriaSocial> lista = historias.stream()
        .map(historia -> new DadosListagemBancoDeHistoriaSocial(historia)).collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/pesquisa/{emailleitor}")
    public ResponseEntity<List<DadosListagemBancoDeHistoriaSocial>> listarHistoriasPropriasPorEmailUsuarioAssociado(@PathVariable String emailleitor) {
        List<BancoDeHistoriaSocial> historias = repositoryHistSoc.findByAllEmailBancoDeHistoriaUsuarioAssociado(emailleitor);
        List<DadosListagemBancoDeHistoriaSocial> lista = historias.stream()
                                               .map(historia -> new DadosListagemBancoDeHistoriaSocial(historia))
                                               .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/{idbancodehistoria}/{idusuario}")
    @Transactional
    public ResponseEntity<String> vincularBancoDeHistoriaSocial(@PathVariable Long idbancodehistoria, @PathVariable Long idusuario) {
        if (idbancodehistoria == null || idusuario == null) {
            return ResponseEntity.badRequest().body("Os parâmetros idbancodehistoria e idusuario são obrigatórios.");
        }
    
        repositoryHistSoc.linkUsuarioBancoDeHistorias(idusuario, idbancodehistoria);
        return ResponseEntity.ok("A vinculação foi realizada com sucesso.");
    }

}
