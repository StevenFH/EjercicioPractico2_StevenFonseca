package EjercicioPractico2_StevenFonseca.service;

import EjercicioPractico2_StevenFonseca.domain.Rol;
import EjercicioPractico2_StevenFonseca.repository.RolRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RolService {

    private final RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Transactional(readOnly = true)
    public List<Rol> listarRoles() {
        return rolRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Rol> obtenerRol(Long id) {
        return rolRepository.findById(id);
    }

    @Transactional
    public Rol crearRol(Rol rol) {
        return rolRepository.save(rol);
    }

    @Transactional
    public Rol editarRol(Long id, Rol datos) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado"));
        rol.setNombre(datos.getNombre());
        rol.setDescripcion(datos.getDescripcion());
        return rolRepository.save(rol);
    }

    @Transactional
    public void eliminarRol(Long id) {
        rolRepository.deleteById(id);
    }
}
