package tcc.histsoc.api.controller;

import jakarta.validation.Valid;
import tcc.histsoc.api.domain.HabilidadeSocial;
import tcc.histsoc.api.domain.HistoriaSocial;
import tcc.histsoc.api.repository.AtividadeDeVidaDiariaRepository;
import tcc.histsoc.api.repository.HabilidadeSocialRepository;
import tcc.histsoc.api.repository.HistoriaSocialRepository;
import tcc.histsoc.api.repository.ImagemRepository;
import tcc.histsoc.api.dto.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("historiasocial")
public class HistoriaSocialController {

    @Autowired
    private HistoriaSocialRepository repositoryHistSoc;

    @Autowired
    private ImagemRepository repositoryImg;

    @Autowired
    private HabilidadeSocialRepository repositoryHs;

    @Autowired
    private AtividadeDeVidaDiariaRepository repositoryAvd;

    @PostMapping
    @Transactional
    public ResponseEntity<HistoriaSocial> cadastrar(@RequestBody @Valid DadosCadHisSoc dados, UriComponentsBuilder uriBuilder) {
        var historiaSocial = new HistoriaSocial(dados);
        repositoryHistSoc.save(historiaSocial);
        var uri = uriBuilder.path("/historiasocial/{id}").buildAndExpand(historiaSocial.getId()).toUri();
        return ResponseEntity.created(uri).body(historiaSocial);
    }

    @GetMapping("/pesquisa/historiasproprias/{email}")
    public ResponseEntity<List<DadosListagemHistoriaSocial>> listarHistoriasPropriasPorEmail(@PathVariable String email) {
        List<HistoriaSocial> historias = repositoryHistSoc.findByAllEmailHistoriasProprias(email);
        List<DadosListagemHistoriaSocial> lista = historias.stream()
                                               .map(historia -> new DadosListagemHistoriaSocial(historia))
                                               .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var historia = repositoryHistSoc.getReferenceById(id);
        repositoryHistSoc.delete(historia);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pesquisa/historiapropria/{id}")
    public ResponseEntity<DadosDetalhamentoHistoriaSocial> listarHistoriasPropriasPorId(@PathVariable Long id) {
        Optional<HistoriaSocial> historia = repositoryHistSoc.findById(id);
        //Optional<HabilidadeSocial>habilidadeSoc = repository
        DadosDetalhamentoHistoriaSocial historiaDTO = new DadosDetalhamentoHistoriaSocial(historia.get());
        return ResponseEntity.ok(historiaDTO);
    }

}
