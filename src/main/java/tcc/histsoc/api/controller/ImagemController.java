package tcc.histsoc.api.controller;

import tcc.histsoc.api.domain.Imagem;
import tcc.histsoc.api.service.ImagemService;
import tcc.histsoc.api.dto.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("imagem")
public class ImagemController {

    private ImagemService imagemService = new ImagemService();

   
    @GetMapping("/{id}/{email}")
    public ResponseEntity<List<DadosDetalhamentoImagem>> detalhar(@PathVariable Long id, @PathVariable String email) {
        List<Imagem> imagens = imagemService.findByAllEmailIdImagens(id, email);
        List<DadosDetalhamentoImagem> lista = imagens.stream()
                                               .map(imagem -> new DadosDetalhamentoImagem(imagem))
                                               .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }


    

}
