package EjercicioPractico2_StevenFonseca.repository;

import EjercicioPractico2_StevenFonseca.domain.Usuario;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmailAndActivoTrue(String email);

    List<Usuario> findByRolId(Long rolId);

    List<Usuario> findByFechaCreacionBetween(LocalDateTime inicio, LocalDateTime fin);

    List<Usuario> findByEmailContainingOrNombreContaining(String email, String nombre);

    long countByActivoTrue();

    long countByActivoFalse();

    List<Usuario> findAllByOrderByFechaCreacionAsc();
}
