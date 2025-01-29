package tcc.histsoc.api.controller;

import jakarta.validation.Valid;
import tcc.histsoc.api.domain.HistoriaSocial;
import tcc.histsoc.api.service.HistoriaSocialService;
import tcc.histsoc.api.dto.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("historiasocial")
public class HistoriaSocialController {
    private HistoriaSocialService historiaSocialService = new HistoriaSocialService();
    private static final Logger log = LoggerFactory.getLogger(HistoriaSocialController.class);

    @PostMapping
    @Transactional
    public ResponseEntity<HistoriaSocial> cadastrar(@RequestBody @Valid DadosCadHisSoc dados, UriComponentsBuilder uriBuilder) {
        var historiaSocial = new HistoriaSocial(dados);
        historiaSocialService.save(historiaSocial);
        var uri = uriBuilder.path("/historiasocial/{id}").buildAndExpand(historiaSocial.getId()).toUri();
        return ResponseEntity.created(uri).body(historiaSocial);
    }



    @GetMapping("/pesquisa/historiasproprias/{email}")
    public ResponseEntity<List<DadosListagemHistoriaSocial>> listarHistoriasPropriasPorEmail(@PathVariable String email) {
        List<HistoriaSocial> historias = historiaSocialService.findByAllEmailHistoriasProprias(email);
        List<DadosListagemHistoriaSocial> lista = historias.stream()
                                               .map(historia -> new DadosListagemHistoriaSocial(historia))
                                               .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var historia = historiaSocialService.getReferenceById(id);
        historiaSocialService.delete(historia);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pesquisa/historiapropria/{id}")
    public ResponseEntity<DadosDetalhamentoHistoriaSocial> listarHistoriasPropriasPorId(@PathVariable Long id) {
        Optional<HistoriaSocial> historia = historiaSocialService.findById(id);
        DadosDetalhamentoHistoriaSocial historiaDTO = new DadosDetalhamentoHistoriaSocial(historia.get());
        return ResponseEntity.ok(historiaDTO);
    }

    @PutMapping("/edita/historiapropria/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoHistoriaSocial> atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoHistSoc dados) {
        Optional<HistoriaSocial> historia = historiaSocialService.findById(id);
        HistoriaSocial historiaSocialAtualizada = new HistoriaSocial();
        if(historia.isPresent()){
            historiaSocialAtualizada = historia.get();
            historiaSocialAtualizada.atualizaHistoriaSocial(dados);
            save(historiaSocialAtualizada);
        }
        return ResponseEntity.ok(new DadosDetalhamentoHistoriaSocial(historiaSocialAtualizada));
    }

    @GetMapping("/pesquisa/associa/{emailleitor}")
    public ResponseEntity<List<DadosListagemHistoriaSocial>> listarHistoriasBancoPorEmailUsuarioAssociado(@PathVariable String emailleitor) {
        List<HistoriaSocial> historias = historiaSocialService.findByAllEmailHistoriaUsuarioNotIn(emailleitor);
        List<DadosListagemHistoriaSocial> lista = historias.stream()
                                               .map(historia -> new DadosListagemHistoriaSocial(historia))
                                               .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/pesquisa/leitor/lista/{emailleitor}")
    public ResponseEntity<List<DadosListagemHistoriaSocial>> listarHistoriasBancoPorEmailUsuario(@PathVariable String emailleitor) {
        List<HistoriaSocial> historias = historiaSocialService.findByAllEmailHistoriaUsuarioIn(emailleitor);
        List<DadosListagemHistoriaSocial> lista = historias.stream()
                                               .map(historia -> new DadosListagemHistoriaSocial(historia))
                                               .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/pesquisa/desassocia/{emailleitor}")
    public ResponseEntity<List<DadosListagemHistoriaSocial>> listarHistoriasBancoPorEmailUsuarioDesassociado(@PathVariable String emailleitor) {
        List<HistoriaSocial> historias = historiaSocialService.findByAllEmailHistoriaUsuarioIn(emailleitor);
        List<DadosListagemHistoriaSocial> lista = historias.stream()
                                               .map(historia -> new DadosListagemHistoriaSocial(historia))
                                               .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/associa/{idhistoria}/{idusuario}")
    @Transactional
    public ResponseEntity<DadosMensagem> vincularHistoriaSocial(@PathVariable Long idhistoria, @PathVariable Long idusuario) {
        DadosMensagem mensagem = historiaSocialService.associarUsuarioHistorias(idusuario, idhistoria);
        return ResponseEntity.ok(mensagem);
    }

    @PostMapping("/desassocia/{idhistoria}/{idusuario}")
    @Transactional
    public ResponseEntity<DadosMensagem> desvincularHistoriaSocial(@PathVariable Long idhistoria, @PathVariable Long idusuario) {
        DadosMensagem mensagem = historiaSocialService.desassociarUsuarioHistorias(idusuario, idhistoria);
        return ResponseEntity.ok(mensagem);
    }

}
