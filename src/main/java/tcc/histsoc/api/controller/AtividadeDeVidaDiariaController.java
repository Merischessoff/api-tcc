package tcc.histsoc.api.controller;

import jakarta.validation.Valid;
import tcc.histsoc.api.domain.AtividadeDeVidaDiaria;
import tcc.histsoc.api.repository.AtividadeDeVidaDiariaRepository;
import tcc.histsoc.api.dto.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

@RestController
//@PreAuthorize("hasRole('ROLE_VIEWER') or hasRole('ROLE_EDITOR')")
@RequestMapping("atividadedevidadiaria")
public class AtividadeDeVidaDiariaController {

    @Autowired
    private AtividadeDeVidaDiariaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadAVD dados, UriComponentsBuilder uriBuilder) {
        var atividadedevidadiaria = new AtividadeDeVidaDiaria(dados);
        repository.save(atividadedevidadiaria);
        var uri = uriBuilder.path("/atividadedevidadiaria/{id}").buildAndExpand(atividadedevidadiaria.getId()).toUri();
        return ResponseEntity.created(uri).body(atividadedevidadiaria);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemAVD>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemAVD::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoAVD dados) {
        var atividadeDeVidaDiaria = repository.getReferenceById(dados.id());
        atividadeDeVidaDiaria.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoAVD(atividadeDeVidaDiaria));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var atividadeDeVidaDiaria = repository.getReferenceById(id);
        repository.delete(atividadeDeVidaDiaria);;
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var atividadeDeVidaDiaria = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoAVD(atividadeDeVidaDiaria));
    }

}
