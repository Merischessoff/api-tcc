package tcc.histsoc.api.controller;

import jakarta.validation.Valid;
import tcc.histsoc.api.domain.HabilidadeSocial;
import tcc.histsoc.api.repository.HabilidadeSocialRepository;
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
@RequestMapping("habilidadesocial")
public class HabilidadeSocialController {

    @Autowired
    private HabilidadeSocialRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadHabSoc dados, UriComponentsBuilder uriBuilder) {
        var habilidadeSocial = new HabilidadeSocial(dados);
        repository.save(habilidadeSocial);
        var uri = uriBuilder.path("/habilidadesocial/{id}").buildAndExpand(habilidadeSocial.getId()).toUri();
        return ResponseEntity.created(uri).body(habilidadeSocial);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemHabSoc>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemHabSoc::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoHabSoc dados) {
        var habilidadeSocial = repository.getReferenceById(dados.id());
        habilidadeSocial.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoHabSoc(habilidadeSocial));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var habilidadeSocial = repository.getReferenceById(id);
        repository.delete(habilidadeSocial);;
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var habilidadeSocial = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoHabSoc(habilidadeSocial));
    }

}
