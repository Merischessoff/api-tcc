package tcc.histsoc.api.controller;

import tcc.histsoc.api.Strings;
import tcc.histsoc.api.domain.BancoDeHistoriaSocial;
import tcc.histsoc.api.dto.DadosListagemBancoDeHistoriaSocial;
import tcc.histsoc.api.dto.DadosMensagem;
import tcc.histsoc.api.service.BancoDeHistoriaSocialService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("bancodehistoriasocial")
public class BancoDeHistoriaSocialController {

    BancoDeHistoriaSocialService bancoDeHistoriaSocialService = new BancoDeHistoriaSocialService();
    
    @GetMapping("/pesquisa")
    public ResponseEntity<List<DadosListagemBancoDeHistoriaSocial>> listarBancoDeHistoriasSociais() {
        List<BancoDeHistoriaSocial> historias = bancoDeHistoriaSocialService.findAll();
        List<DadosListagemBancoDeHistoriaSocial> lista = historias.stream()
        .map(historia -> new DadosListagemBancoDeHistoriaSocial(historia)).collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/pesquisa/associa/{emailleitor}")
    public ResponseEntity<List<DadosListagemBancoDeHistoriaSocial>> listarHistoriasBancoPorEmailUsuarioAssociado(@PathVariable String emailleitor) {
        List<BancoDeHistoriaSocial> historias = bancoDeHistoriaSocialService.findByAllEmailBancoDeHistoriaUsuarioNotIn(emailleitor);
        List<DadosListagemBancoDeHistoriaSocial> lista = historias.stream()
                                               .map(historia -> new DadosListagemBancoDeHistoriaSocial(historia))
                                               .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/pesquisa/leitor/lista/{emailleitor}")
    public ResponseEntity<List<DadosListagemBancoDeHistoriaSocial>> listarHistoriasBancoPorEmailUsuario(@PathVariable String emailleitor) {
        List<BancoDeHistoriaSocial> historias = bancoDeHistoriaSocialService.findByAllEmailBancoDeHistoriaUsuarioIn(emailleitor); 
        List<DadosListagemBancoDeHistoriaSocial> lista = historias.stream()
                                               .map(historia -> new DadosListagemBancoDeHistoriaSocial(historia))
                                               .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/pesquisa/desassocia/{emailleitor}")
    public ResponseEntity<List<DadosListagemBancoDeHistoriaSocial>> listarHistoriasBancoPorEmailUsuarioDesassociado(@PathVariable String emailleitor) {
        List<BancoDeHistoriaSocial> historias = bancoDeHistoriaSocialService.findByAllEmailBancoDeHistoriaUsuarioIn(emailleitor);
        List<DadosListagemBancoDeHistoriaSocial> lista = historias.stream()
                                               .map(historia -> new DadosListagemBancoDeHistoriaSocial(historia))
                                               .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/associa/{idbancodehistoria}/{idusuario}")
    @Transactional
    public ResponseEntity<DadosMensagem> vincularBancoDeHistoriaSocial(@PathVariable Long idbancodehistoria, @PathVariable Long idusuario) {
        DadosMensagem mensagem = bancoDeHistoriaSocialService.associarUsuarioBancoDeHistorias(idusuario, idbancodehistoria);
        return ResponseEntity.ok(mensagem);
    }

    @PostMapping("/desassocia/{idbancodehistoria}/{idusuario}")
    @Transactional
    public ResponseEntity<DadosMensagem> desvincularBancoDeHistoriaSocial(@PathVariable Long idbancodehistoria, @PathVariable Long idusuario) {
        DadosMensagem mensagem = bancoDeHistoriaSocialService.desassociarUsuarioBancoDeHistorias(idusuario, idbancodehistoria);
        return ResponseEntity.ok(mensagem);
    }

}
