package tcc.histsoc.api.controller;

import tcc.histsoc.api.domain.BancoDeHistoriaSocial;
import tcc.histsoc.api.dto.DadosListagemBancoDeHistoriaSocial;
import tcc.histsoc.api.repository.BancoDeHistoriaSocialRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
