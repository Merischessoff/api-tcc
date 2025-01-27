package tcc.histsoc.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tcc.histsoc.api.Strings;
import tcc.histsoc.api.domain.BancoDeHistoriaSocial;
import tcc.histsoc.api.dto.DadosMensagem;
import tcc.histsoc.api.repository.BancoDeHistoriaSocialRepository;

@Service
public class BancoDeHistoriaSocialService{

    @Autowired
    private BancoDeHistoriaSocialRepository repositoryHistSoc;

    public List<BancoDeHistoriaSocial> findAll() {
        return repositoryHistSoc.findAll();

    }

    public List<BancoDeHistoriaSocial> findByAllEmailBancoDeHistoriaUsuarioNotIn(String emailleitor) {
        return repositoryHistSoc.findByAllEmailBancoDeHistoriaUsuarioNotIn(emailleitor);
    }

    public List<BancoDeHistoriaSocial> findByAllEmailBancoDeHistoriaUsuarioIn(String emailleitor) {
        return repositoryHistSoc.findByAllEmailBancoDeHistoriaUsuarioIn(emailleitor);
    }

    public DadosMensagem associarUsuarioBancoDeHistorias(Long idusuario, Long idbancodehistoria) {
        DadosMensagem mensagem = new DadosMensagem();
        if (idbancodehistoria == null || idusuario == null) {
            mensagem.setMensagem(Strings.MSG_PARAM_ID_USU_ID_BANC_DE_HIST);
        }else{
            mensagem.setMensagem(Strings.MSG_VINC_REALIZ);
            repositoryHistSoc.associarUsuarioBancoDeHistorias(idusuario, idbancodehistoria);
        }
        return mensagem;
    }

    public DadosMensagem desassociarUsuarioBancoDeHistorias(Long idusuario, Long idbancodehistoria) {
        DadosMensagem mensagem = new DadosMensagem();
        if (idbancodehistoria == null || idusuario == null) {
            mensagem.setMensagem(Strings.MSG_PARAM_ID_USU_ID_BANC_DE_HIST);
        }else{
            mensagem.setMensagem(Strings.MSG_DESV);
            desassociarUsuarioBancoDeHistorias(idusuario, idbancodehistoria);
        }    
      return mensagem;
    }




}