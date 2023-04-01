package tcc.histsoc.api.controller;

import jakarta.validation.Valid;
import tcc.histsoc.api.domain.Usuario;
import tcc.histsoc.api.repository.UsuarioRepository;
import tcc.histsoc.api.dto.*;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping("/cadastro")
    @Transactional
    public ResponseEntity<Usuario> cadastrar(@RequestBody @Valid DadosCadUsuario dados, UriComponentsBuilder uriBuilder) {
        var usuario = new Usuario(dados);
        repository.save(usuario);
        var uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }


    @GetMapping("/pesquisa/{email}")
    public ResponseEntity<DadosTipoUsuario> pesquisaUsuarioPorEmail(@PathVariable String email) {
        var usuario = repository.findByEmailUsuario(email);
        return ResponseEntity.ok(new DadosTipoUsuario(usuario));
    }
    
    @GetMapping("/pesquisa/leitorvinculado/{email}")
    public ResponseEntity<List<DadosListagemUsuario>> listarUsuariosLeitoresPorEmail(@PathVariable String email) {
        List<Usuario> usuarios = repository.findByAllEmailUsuariosLeitores(email);
        List<DadosListagemUsuario> lista = usuarios.stream()
                                               .map(usuario -> new DadosListagemUsuario(usuario))
                                               .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/edita/leitor/{email}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoUsuario> atualizar(@PathVariable String email, @RequestBody @Valid DadosAtualizacaoUsuario dados) {
        var usuario = repository.findByEmailUsuario(email);
        usuario.atualizarInformacoes(dados);
        repository.save(usuario);
        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }

    @DeleteMapping("/exclui/{email}")
    @Transactional
    public ResponseEntity<Usuario> excluir(@PathVariable String email) {
        var usuario = repository.findByEmailUsuario(email);
        repository.delete(usuario);;
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoUsuario> detalhar(@PathVariable Long id) {
        var usuario = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }


}
