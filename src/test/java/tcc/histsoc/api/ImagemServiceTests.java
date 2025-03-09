package tcc.histsoc.api;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import tcc.histsoc.api.domain.Imagem;
import tcc.histsoc.api.repository.ImagemRepository;
import tcc.histsoc.api.service.ImagemService;

public class ImagemServiceTests {
    @Test
    public void test_returns_imagem_list_when_valid_parameters() {
        // Arrange
        Long id = 1L;
        String email = "test@example.com";
        List<Imagem> expectedImages = Arrays.asList(new Imagem(), new Imagem());
        ImagemRepository imagemRepository = Mockito.mock(ImagemRepository.class);
        Mockito.when(imagemRepository.findByAllEmailIdImagens(email, id)).thenReturn(expectedImages);
        ImagemService imagemService = new ImagemService();
        ReflectionTestUtils.setField(imagemService, "imagemRepository", imagemRepository);
        // Act
        List<Imagem> result = imagemService.findByAllEmailIdImagens(id, email);
        // Assert
        assertEquals(expectedImages, result);
        Mockito.verify(imagemRepository).findByAllEmailIdImagens(email, id);
    }

}
