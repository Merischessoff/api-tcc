package tcc.histsoc.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tcc.histsoc.api.Strings;
import tcc.histsoc.api.domain.HistoriaSocial;
import tcc.histsoc.api.dto.DadosMensagem;
import tcc.histsoc.api.repository.HistoriaSocialRepository;

@Service
public class HistoriaSocialService {

    @Autowired
    private HistoriaSocialRepository historiaSocialRepository;

    public void save(HistoriaSocial historiaSocial) {
        historiaSocialRepository.save(historiaSocial);
    }

    public List<HistoriaSocial> findByAllEmailHistoriasProprias(String email) {
       return historiaSocialRepository.findByAllEmailHistoriasProprias(email);
    }

    public HistoriaSocial getReferenceById(Long id) {
        return historiaSocialRepository.getReferenceById(id);
    }

    public void delete(HistoriaSocial historia) {
        historiaSocialRepository.delete(historia);
    }

    public Optional<HistoriaSocial> findById(Long id) {
        return historiaSocialRepository.findById(id);
    }

    public List<HistoriaSocial> findByAllEmailHistoriaUsuarioNotIn(String emailleitor) {
        return historiaSocialRepository.findByAllEmailHistoriaUsuarioNotIn(emailleitor);
    }

    public List<HistoriaSocial> findByAllEmailHistoriaUsuarioIn(String emailleitor) {
        return historiaSocialRepository.findByAllEmailHistoriaUsuarioIn(emailleitor);
    }

    public DadosMensagem desassociarUsuarioHistorias(Long idusuario, Long idhistoria) {
        DadosMensagem mensagem = new DadosMensagem();
        if (idhistoria == null || idusuario == null) {
            mensagem.setMensagem(Strings.MSG_PARAM_ID_USU_ID_HIST);
        }else{
            historiaSocialRepository.desassociarUsuarioHistorias(idusuario, idhistoria);
            mensagem.setMensagem(Strings.MSG_DESVINC_REALIZ);
        }
        return mensagem;
    }

    public DadosMensagem associarUsuarioHistorias(Long idusuario, Long idhistoria) {
        DadosMensagem mensagem = new DadosMensagem();
        if (idhistoria == null || idusuario == null) {
            mensagem.setMensagem(Strings.MSG_PARAM_ID_USU_ID_HIST);
         }else{
            historiaSocialRepository.associarUsuarioHistorias(idusuario, idhistoria);
            mensagem.setMensagem(Strings.MSG_VINC_REALIZ);
         }
         return mensagem;
    }
    
}
