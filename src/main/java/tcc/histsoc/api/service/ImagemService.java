package tcc.histsoc.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tcc.histsoc.api.domain.Imagem;
import tcc.histsoc.api.repository.ImagemRepository;

@Service
public class ImagemService {
    @Autowired
    private ImagemRepository imagemRepository;

    public List<Imagem> findByAllEmailIdImagens(Long id, String email) {
        return imagemRepository.findByAllEmailIdImagens(email, id);
    }
}
