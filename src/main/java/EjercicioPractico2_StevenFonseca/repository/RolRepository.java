package EjercicioPractico2_StevenFonseca.repository;

import EjercicioPractico2_StevenFonseca.domain.Rol;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByNombre(String nombre);
}
