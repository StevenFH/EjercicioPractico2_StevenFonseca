package EjercicioPractico2_StevenFonseca.service;

import EjercicioPractico2_StevenFonseca.domain.Usuario;
import EjercicioPractico2_StevenFonseca.domain.Rol;
import EjercicioPractico2_StevenFonseca.repository.UsuarioRepository;
import EjercicioPractico2_StevenFonseca.repository.RolRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          RolRepository rolRepository,
                          PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Usuario> obtenerUsuario(Long id) {
        return usuarioRepository.findById(id);
    }

    @Transactional
    public Usuario crearUsuario(Usuario usuario, String rolNombre) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setFechaCreacion(LocalDateTime.now());

        Rol rol = rolRepository.findByNombre(rolNombre)
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado: " + rolNombre));
        usuario.setRol(rol);

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario editarUsuario(Long id, Usuario datos) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        usuario.setNombre(datos.getNombre());
        usuario.setApellido(datos.getApellido());
        usuario.setEmail(datos.getEmail());
        usuario.setActivo(datos.isActivo());

        if (datos.getPassword() != null && !datos.getPassword().isBlank()) {
            usuario.setPassword(passwordEncoder.encode(datos.getPassword()));
        }

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Usuario> usuariosPorRol(Long rolId) {
        return usuarioRepository.findByRolId(rolId);
    }

    @Transactional(readOnly = true)
    public List<Usuario> usuariosPorFecha(LocalDateTime inicio, LocalDateTime fin) {
        return usuarioRepository.findByFechaCreacionBetween(inicio, fin);
    }

    @Transactional(readOnly = true)
    public List<Usuario> usuariosPorCoincidencia(String texto) {
        return usuarioRepository.findByEmailContainingOrNombreContaining(texto, texto);
    }

    @Transactional(readOnly = true)
    public long contarActivos() {
        return usuarioRepository.countByActivoTrue();
    }

    @Transactional(readOnly = true)
    public long contarInactivos() {
        return usuarioRepository.countByActivoFalse();
    }

    @Transactional(readOnly = true)
    public List<Usuario> usuariosOrdenadosPorFecha() {
        return usuarioRepository.findAllByOrderByFechaCreacionAsc();
    }
}
