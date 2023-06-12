package tcc.histsoc.api.controller;

import jakarta.validation.Valid;
import tcc.histsoc.api.domain.HistoriaSocial;
import tcc.histsoc.api.repository.HistoriaSocialRepository;
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

    @PostMapping
    @Transactional
    public ResponseEntity<HistoriaSocial> cadastrar(@RequestBody @Valid DadosCadHisSoc dados, UriComponentsBuilder uriBuilder) {
        System.out.println(dados.toString());
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
        DadosDetalhamentoHistoriaSocial historiaDTO = new DadosDetalhamentoHistoriaSocial(historia.get());
        return ResponseEntity.ok(historiaDTO);
    }

    @PutMapping("/edita/historiapropria/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoHistoriaSocial> atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoHistSoc dados) {
        Optional<HistoriaSocial> historia = repositoryHistSoc.findById(id);
        HistoriaSocial historiaSocialAtualizada = new HistoriaSocial();
        if(historia.isPresent()){
            historiaSocialAtualizada = historia.get();
            historiaSocialAtualizada.atualizaHistoriaSocial(dados);
            repositoryHistSoc.save(historiaSocialAtualizada);
        }
        return ResponseEntity.ok(new DadosDetalhamentoHistoriaSocial(historiaSocialAtualizada));
    }

    @GetMapping("/pesquisa/associa/{emailleitor}")
    public ResponseEntity<List<DadosListagemHistoriaSocial>> listarHistoriasBancoPorEmailUsuarioAssociado(@PathVariable String emailleitor) {
        List<HistoriaSocial> historias = repositoryHistSoc.findByAllEmailHistoriaUsuarioNotIn(emailleitor);
        List<DadosListagemHistoriaSocial> lista = historias.stream()
                                               .map(historia -> new DadosListagemHistoriaSocial(historia))
                                               .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/pesquisa/leitor/lista/{emailleitor}")
    public ResponseEntity<List<DadosListagemHistoriaSocial>> listarHistoriasBancoPorEmailUsuario(@PathVariable String emailleitor) {
        List<HistoriaSocial> historias = repositoryHistSoc.findByAllEmailHistoriaUsuarioIn(emailleitor);
        List<DadosListagemHistoriaSocial> lista = historias.stream()
                                               .map(historia -> new DadosListagemHistoriaSocial(historia))
                                               .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/pesquisa/desassocia/{emailleitor}")
    public ResponseEntity<List<DadosListagemHistoriaSocial>> listarHistoriasBancoPorEmailUsuarioDesassociado(@PathVariable String emailleitor) {
        List<HistoriaSocial> historias = repositoryHistSoc.findByAllEmailHistoriaUsuarioIn(emailleitor);
        List<DadosListagemHistoriaSocial> lista = historias.stream()
                                               .map(historia -> new DadosListagemHistoriaSocial(historia))
                                               .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/associa/{idhistoria}/{idusuario}")
    @Transactional
    public ResponseEntity<DadosMensagem> vincularHistoriaSocial(@PathVariable Long idhistoria, @PathVariable Long idusuario) {
        DadosMensagem mensagem = new DadosMensagem();
        if (idhistoria == null || idusuario == null) {
           mensagem.setMensagem("Os parâmetros idhistoria e idusuario são obrigatórios.");
        }else{
            repositoryHistSoc.associarUsuarioHistorias(idusuario, idhistoria);
            mensagem.setMensagem("A desvinculação foi realizada com sucesso.");
        }
        return ResponseEntity.ok(mensagem);
    }

    @PostMapping("/desassocia/{idhistoria}/{idusuario}")
    @Transactional
    public ResponseEntity<DadosMensagem> desvincularHistoriaSocial(@PathVariable Long idhistoria, @PathVariable Long idusuario) {
        DadosMensagem mensagem = new DadosMensagem();
        if (idhistoria == null || idusuario == null) {
            mensagem.setMensagem("Os parâmetros idhistoria e idusuario são obrigatórios.");
        }else{
            repositoryHistSoc.desassociarUsuarioHistorias(idusuario, idhistoria);
            mensagem.setMensagem("A desvinculação foi realizada com sucesso.");
        }
        return ResponseEntity.ok(mensagem);
    }

}
