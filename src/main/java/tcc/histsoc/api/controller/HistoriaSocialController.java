package tcc.histsoc.api.controller;

import jakarta.validation.Valid;
import tcc.histsoc.api.domain.HistoriaSocial;
import tcc.histsoc.api.repository.HistoriaSocialRepository;
import tcc.histsoc.api.dto.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

@RestController
@RequestMapping("historiasocial")
public class HistoriaSocialController {

    @Autowired
    private HistoriaSocialRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadHisSoc dados, UriComponentsBuilder uriBuilder) {
        var historiaSocial = new HistoriaSocial(dados);
        repository.save(historiaSocial);
        var uri = uriBuilder.path("/historiasocial/{id}").buildAndExpand(historiaSocial.getId()).toUri();
        return ResponseEntity.created(uri).body(historiaSocial);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemHistSoc>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemHistSoc::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoHistSoc dados) {
        var historiaSocial = repository.getReferenceById(dados.id());
        historiaSocial.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoHistSoc(historiaSocial));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var historiaSocial = repository.getReferenceById(id);
        repository.delete(historiaSocial);;
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var historiaSocial = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoHistSoc(historiaSocial));
    }

}
