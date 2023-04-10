package tcc.histsoc.api.controller;

import jakarta.validation.Valid;
import tcc.histsoc.api.domain.HistoriaSocial;
import tcc.histsoc.api.domain.Imagem;
import tcc.histsoc.api.repository.HistoriaSocialRepository;
import tcc.histsoc.api.repository.ImagemRepository;
import tcc.histsoc.api.dto.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("imagem")
public class ImagemController {

    @Autowired
    private ImagemRepository repository;

   
    @GetMapping("/{id}/{email}")
    public ResponseEntity<List<DadosDetalhamentoImagem>> detalhar(@PathVariable Long id, @PathVariable String email) {
        List<Imagem> imagens = repository.findByAllEmailIdImagens(email, id);
        List<DadosDetalhamentoImagem> lista = imagens.stream()
                                               .map(imagem -> new DadosDetalhamentoImagem(imagem))
                                               .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }
    

}
