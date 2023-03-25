package tcc.histsoc.api.controller;

import jakarta.validation.Valid;
import tcc.histsoc.api.domain.Imagem;
import tcc.histsoc.api.repository.ImagemRepository;
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
@RequestMapping("imagem")
public class ImagemController {

    @Autowired
    private ImagemRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadImg dados, UriComponentsBuilder uriBuilder) {
        var imagem = new Imagem(dados);
        repository.save(imagem);
        var uri = uriBuilder.path("/imagem/{id}").buildAndExpand(imagem.getId()).toUri();
        return ResponseEntity.created(uri).body(imagem);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemImagem>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemImagem::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoImg dados) {
        var imagem = repository.getReferenceById(dados.id());
        imagem.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoImagem(imagem));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var imagem = repository.getReferenceById(id);
        repository.delete(imagem);;
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var imagem = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoImagem(imagem));
    }

}
