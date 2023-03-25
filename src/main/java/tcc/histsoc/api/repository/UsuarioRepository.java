package tcc.histsoc.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import tcc.histsoc.api.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByEmail(String email);

    @Query(value = "SELECT * FROM usuario u WHERE u.email = ?1", nativeQuery = true)
    Usuario findByEmailTipoUsuario(String email);
}
