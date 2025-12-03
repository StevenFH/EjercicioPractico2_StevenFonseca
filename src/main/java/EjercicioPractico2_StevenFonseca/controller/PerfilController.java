package EjercicioPractico2_StevenFonseca.controller;

import EjercicioPractico2_StevenFonseca.domain.Usuario;
import EjercicioPractico2_StevenFonseca.repository.UsuarioRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PerfilController {

    private final UsuarioRepository usuarioRepository;

    public PerfilController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/perfil")
    public String perfil(@AuthenticationPrincipal User user, Model model) {
        Usuario usuario = usuarioRepository.findByEmailAndActivoTrue(user.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        model.addAttribute("usuario", usuario);
        return "perfil";
    }
}
