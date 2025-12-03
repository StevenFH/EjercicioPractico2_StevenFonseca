package EjercicioPractico2_StevenFonseca.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {

    @GetMapping("/")
    public String redireccionSegunRol(Authentication auth) {
        if (auth == null) {
            return "redirect:/login";
        }

        if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "redirect:/usuarios/listado";
        } else if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_PROFESOR"))) {
            return "redirect:/reportes";
        } else if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ESTUDIANTE"))) {
            return "redirect:/perfil";
        }

        return "redirect:/login?error=true";
    }
}
